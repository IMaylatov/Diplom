package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class SpreadPersonToClusterImpl implements SpreadPersonToCluster{
    @Override
    public List<Cluster> simpleSpread(List<Person> persons, int k) {
        if (persons == null)
            throw new IllegalArgumentException("Persons = null");
        if ((k <= 0) && (persons.size() >= k))
            throw new IllegalArgumentException(
                    String.format("������� ������ ��������� {k=%d; person count = %d}", k, persons.size()));

        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++)
            clusters.add(new Cluster());

        int indexCurrentCluster = 0;
        for(Person person : persons){
            clusters.get(indexCurrentCluster % k).addPerson(person);
            indexCurrentCluster++;
        }

        return clusters;
    }

    @Override
    public List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric) {
        if ((clusters == null) || (persons == null) || (metric == null))
            throw new IllegalArgumentException("clusters = " + clusters +
                                                ";persons = " + persons +
                                                  ";metric = " + metric);
        if (persons.size() < clusters.size() || persons.size() < 0)
            throw new IllegalArgumentException("clusters count = " + clusters.size() +
                                                ";persons count = " + persons.size());

//        for(Person person : persons){
//            Iterator<Cluster> clusterIterator = clusters.iteratorRates();
//            Cluster minCluster = clusterIterator.next();
//            double minDistance = metric.compare(person, minCluster);
//            while(clusterIterator.hasNext()){
//                Cluster k1 = clusterIterator.next();
//                if (minDistance > metric.compare(person, k1))
//                    minCluster = k1;
//            }
//        }

        return persons;
    }
}
