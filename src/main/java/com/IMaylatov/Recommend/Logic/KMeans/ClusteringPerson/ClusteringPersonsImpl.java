package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Logic.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Logic.KMeans.MoverCluster.MoverClusterImpl;
import com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople.SpreadPerson;
import com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople.SpreadPersonImpl;
import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ClusteringPersonsImpl implements ClusteringPersons{
    private final int MAX_ITERATION = 10;

    @Override
    public List<Cluster> clustering(List<Person> persons, int k, Metric metric) {
        SpreadPerson spreadPerson = new SpreadPersonImpl();
        List<Cluster> clusters = spreadPerson.evenlySpread(persons, k);

        boolean isMove = false;
        int i = 0;
        MoverCluster mover = new MoverClusterImpl();
        do {
            spreadPerson.distanceSpread(clusters, persons, metric);
            for (Cluster cluster : clusters)
                if (mover.move(cluster))
                    isMove = true;
        }while ((isMove) && (i++ < MAX_ITERATION));

        return clusters;
    }
}
