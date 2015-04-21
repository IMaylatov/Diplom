package com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class SpreadPersonInCluster implements SpreadPersonInClusterable {
    @Override
    public List<Cluster> simpleSpread(List<Person> persons, int k, BuilderRatesable formingRateInCluster) {
        if (persons == null)
            throw new IllegalArgumentException("Persons = null");
        if ((k <= 0) && (persons.size() >= k))
            throw new IllegalArgumentException(
                    String.format("Неверно заданы параметры {k=%d; person count = %d}", k, persons.size()));

        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++)
            clusters.add(new Cluster());

        int indexCurrentCluster = 0;
        for(Person person : persons){
            clusters.get(indexCurrentCluster % k).addPerson(person);
            indexCurrentCluster++;
        }

        for(Cluster cluster : clusters)
            formingRateInCluster.form(cluster);

        return clusters;
    }

//    @Override
//    public List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric) {
//        if ((clusters == null) || (persons == null) || (metric == null))
//            throw new IllegalArgumentException("clusters = " + clusters +
//                                                ";persons = " + persons +
//                                                  ";metric = " + metric);
//        if (persons.size() < clusters.size() || persons.size() < 0)
//            throw new IllegalArgumentException("clusters count = " + clusters.size() +
//                                                ";persons count = " + persons.size());
//
//        for(Person person : persons){
//            Iterator<Cluster> clusterIterator = clusters.iterator();
//            Cluster nearCluster = clusterIterator.next();
//            double minDistance;
//            try{
//                minDistance = metric.compare(person, nearCluster);
//            }catch(IllegalArgumentException e){
//                minDistance = Double.MAX_VALUE;
//            }
//            while(clusterIterator.hasNext()){
//                Cluster k1 = clusterIterator.next();
//                double distance;
//                try{
//                    distance = metric.compare(person, k1);
//                }catch (IllegalArgumentException ex){
//                    distance = Double.MAX_VALUE;
//                }
//                if (minDistance > distance) {
//                    nearCluster = k1;
//                    minDistance = distance;
//                }
//            }
//            person.setCluster(nearCluster);
//        }
//
//        return persons;
//    }
}
