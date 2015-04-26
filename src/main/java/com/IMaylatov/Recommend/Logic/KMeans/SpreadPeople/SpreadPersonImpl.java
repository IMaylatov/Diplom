package com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople;

import com.IMaylatov.Recommend.Logic.KMeans.BuilderRates.BuilderRates;
import com.IMaylatov.Recommend.Logic.KMeans.BuilderRates.BuilderRatesImpl;
import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class SpreadPersonImpl implements SpreadPerson {
    @Override
    public List<Cluster> evenlySpread(List<Person> persons, int k){
        if (k <= 0)
            throw new IllegalArgumentException(
                    String.format("Неверно заданы параметры {k=%d; personId count = %d}", k, persons.size()));

        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++)
            clusters.add(new Cluster());

        int indexCurrentCluster = 0;
        for(Person person : persons){
            clusters.get(indexCurrentCluster % k).getPersons().add(person);
            indexCurrentCluster++;
        }

        BuilderRates builderRates = new BuilderRatesImpl();
        for(Cluster cluster : clusters)
            builderRates.build(cluster);

        return clusters;
    }

    @Override
    public void distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric) {
        if ((clusters == null) || (persons == null) || (metric == null))
            throw new IllegalArgumentException("clusters = " + clusters +
                                                ";persons = " + persons +
                                                  ";metric = " + metric);
        if (persons.size() < clusters.size() || persons.size() <= 0)
            throw new IllegalArgumentException("clusters count = " + clusters.size() +
                                                ";persons count = " + persons.size());

        for(Person person : persons){
            Cluster nearcluster = clusters.stream().min(
                    Comparator.comparing(cluster -> metric.compare(person, cluster))).get();
            person.setCluster(nearcluster);
            nearcluster.getPersons().add(person);
        }
    }
}
