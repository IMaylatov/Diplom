package com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople;

import com.IMaylatov.Recommend.Logic.KMeans.BuilderRates.BuilderRates;
import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@Service("SpreadPerson")
public class SpreadPersonImpl implements SpreadPerson {
    @Autowired
    private BuilderRates builderRates;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private PersonDao personDao;

    @Override
    public List<Cluster> evenlySpread(List<Person> persons, int k){
        if ((k <= 0) && (persons.size() >= k))
            throw new IllegalArgumentException(
                    String.format("Неверно заданы параметры {k=%d; person count = %d}", k, persons.size()));

        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            Cluster cluster = new Cluster();
            clusters.add(cluster);
            clusterDao.save(cluster);
        }

        int indexCurrentCluster = 0;
        for(Person person : persons){
            clusters.get(indexCurrentCluster % k).getPersons().add(person);
            indexCurrentCluster++;
        }

        clusters.stream().forEach(cluster -> clusterDao.update(cluster));
        clusterDao.flush();

        for(Cluster cluster : clusters)
            builderRates.build(cluster);

        return clusters;
    }

    @Override
    public List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric) {
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
            personDao.update(person);
        }

        return persons;
    }
}
