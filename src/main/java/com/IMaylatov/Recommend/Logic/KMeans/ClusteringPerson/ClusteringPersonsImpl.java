package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Logic.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople.SpreadPerson;
import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster.RateClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
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
    private ClusterDao clusterDAO;
    @Autowired
    private RateClusterDao rateClusterDAO;
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private SpreadPerson spread;
    @Autowired
    private MoverCluster mover;

    private final int MAX_ITERATION = 10;

    @Override
    public void clustering(int k, Metric metric) {
        List<Person> persons = personDAO.list();
        List<Cluster>  clusters = spread.evenlySpread(persons, k);

        boolean isMove = false;
        int i = 0;
        do {
            spread.distanceSpread(clusters, persons, metric);
            for (Cluster cluster : clusters)
                if (mover.move(cluster))
                    isMove = true;
        }while ((isMove) && (i++ < MAX_ITERATION));
    }
}
