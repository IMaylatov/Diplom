package com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate;

import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson.RatePersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Service("CalculaterPredicate")
public class CalculaterPredicateImpl implements CalculaterPredicate {
    private final int L2 = 25;
    private final int L3 = 10;

    @Autowired
    private SongDao songDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private RatePersonDao ratePersonDao;
    @Autowired
    private DbUtil dbUtil;

    @Override
    public void calculate(Cluster cluster) {
        if(cluster.getCountRate() <= 0)
            throw new IllegalArgumentException("Invalid count rate in cluster, count = " + cluster.getCountRate());
        float average = (float) cluster.getSummaRate() / (float) cluster.getCountRate();

        clusterDao.loadPersons(cluster);
        List<Person> personsInCluster = cluster.getPersons();
        List<Song> songInCluster = songDao.songsInCluster(cluster);

        for(Person person : personsInCluster){
            float summa = 0;
            personDao.loadRates(person);
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                summa += rate.getValue() - average;
            }
            summa /= (L2 + person.getRates().size());
            person.setPredicate(summa);

            personDao.update(person);
        }

        for(Song song : songInCluster){
            List<RatePerson> rateForSongInCluster = ratePersonDao.getRateForSongInCluster(song, cluster);
            float summa = 0;
            for(RatePerson rate : rateForSongInCluster){
                summa += rate.getValue() - average - rate.getPerson().getPredicate();
            }
            summa /= (L3 + rateForSongInCluster.size());
            song.getPredicates().put(cluster, summa);
            songDao.update(song);
        }
    }

    @Override
    public void calculate(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            calculate(cluster);
    }
}
