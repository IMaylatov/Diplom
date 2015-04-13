package com.IMaylatov.Recommend.Business.KMeans;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInClusterable;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverClusterable;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInCluster;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInClusterable;
import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */


@Repository("ClusteringPersons")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ClusteringPersonsImpl implements ClusteringPersons{
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private RateClusterDAO rateClusterDAO;
    @Autowired
    private PersonDAO personDAO;

    private final int MAX_ITERATION = 10;

    /**
     * Чтобы распределить пользователей по кластерам, кластера сначала надо очистить и собрать их заново.
     * Распределим пользователей по кластерам в порядке их очередности. По оценкам этих пользователей
     * сформируем оценки кластера. Оценки кластера можно назвать центроидом. Так как кластер имеет центр,
     * то пользователи могут найти к нему расстояние и присоединиться к самому ближайшему кластеру.
     * Теперь кластер знает о пользователях, которые ему принадлежат, а значит может передвигаться в центр их масс.
     * Пока центроид двигается, нужно перераспределять пользователей и двигать центроид в центр масс
     */
    @Override
    public void spread(int k,  Metric metric,
                               SpreadPersonInClusterable spread,
                               FormingRateInClusterable formingRateInCluster,
                               MoverClusterable moverCluster) {
        rateClusterDAO.deleteAll();
        clusterDAO.deleteAll();

        List<Person> persons = personDAO.list();
        List<Cluster>  clusters = spread.simpleSpread(persons, k, formingRateInCluster);

        boolean isMove = false;
        int i = 0;
        do {
            spread.distanceSpread(clusters, persons, metric);
            for (Cluster cluster : clusters)
                if (moverCluster.move(cluster))
                    isMove = true;
        }while ((isMove) && (i++ < MAX_ITERATION));

        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        for(Person person : persons)
            personDAO.save(person);
    }
}
