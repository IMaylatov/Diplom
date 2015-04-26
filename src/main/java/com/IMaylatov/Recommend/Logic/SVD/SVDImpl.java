package com.IMaylatov.Recommend.Logic.SVD;

import com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson.ClusteringPersons;
import com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson.ClusteringPersonsImpl;
import com.IMaylatov.Recommend.Logic.Metric.Euclid;
import com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate.CalculaterPredicate;
import com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate.CalculaterPredicateImpl;
import com.IMaylatov.Recommend.Logic.SVD.GradientDown.GradientDown;
import com.IMaylatov.Recommend.Logic.SVD.GradientDown.GradientDownImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster.RateClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
@Repository("SVD")
public class SVDImpl implements SVD {
    @Autowired
    private ClusterDao clusterDAO;
    @Autowired
    private RateClusterDao rateClusterDAO;
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private SongDao songDao;
    @Autowired
    private PersonPredicateDao personPredicateDAO;


    @Override
    public void calculatePredicate() {
        rateClusterDAO.deleteAll();
        clusterDAO.deleteAll();

        List<Person> persons = personDAO.listWithoutLazy();
        Set<Song> songSet = new HashSet<>();
        for(Person person : persons)
            for(Map.Entry<Song, Integer> rate : person.getRates().entrySet())
                songSet.add(rate.getKey());
        List<Song> songs = new ArrayList<>();
        for(Song song : songSet)
            songs.add(song);

        int k = persons.size() > 100 ? persons.size() / 100 : 1;

        ClusteringPersons clusteringPersons = new ClusteringPersonsImpl();
        List<Cluster> clusters = clusteringPersons.clustering(persons, k, new Euclid());

        CalculaterPredicate calculaterPredicate = new CalculaterPredicateImpl();
        calculaterPredicate.calculate(clusters);

        GradientDown gradientDown = new GradientDownImpl();
        gradientDown.down(clusters);

        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);
        for(Person person : persons)
            personDAO.update(person);
        for(Song song : songs)
            songDao.update(song);
    }
}
