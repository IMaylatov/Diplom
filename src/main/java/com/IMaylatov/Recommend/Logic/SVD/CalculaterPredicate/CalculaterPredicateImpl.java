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

        clusterDao.loadRates(cluster);
        for(Entry<Song, Integer> rate : cluster.getRates().entrySet()){
            Song song = rate.getKey();
            songDao.loadPredicates(song);
            List<Object[]> songsInfo = dbUtil.retrieve(
                    String.format("Select sum(RatePerson.Value - %s), count(RatePerson.*)" +
                                  " from RatePerson" +
                                  " inner join Person on RatePerson.PersonId = Person.Id and Person.ClusterId = %d" +
                                  " where RatePerson.SongId = %d" +
                                  " having count(RatePerson.*) > 0"
                        , average
                        , cluster.getId()
                        , song.getId())
            );
            if (songsInfo.size() > 0)
                song.getPredicates().put(cluster,
                        Float.parseFloat(songsInfo.get(0)[0].toString())
                                / (L2 + Integer.parseInt(songsInfo.get(0)[1].toString())));
            songDao.update(song);
        }

        clusterDao.loadPersons(cluster);
        for(Person person : cluster.getPersons()){
            List<Object[]> personInfo = dbUtil.retrieve(
                    String.format("Select sum(RatePerson.Value - %s - SongPredicate.Value), count(RatePerson.*)" +
                            " from RatePerson" +
                            " inner join Person on RatePerson.PersonId = Person.Id and Person.Id = %d and Person.ClusterId = %d" +
                            " inner join Song on RatePerson.SongId = Song.Id" +
                            " inner join SongPredicate on Song.Id = SongPredicate.SongId and SongPredicate.ClusterId = %d" +
                            " having count(RatePerson.*) > 0"
                            , average
                            , person.getId()
                            , cluster.getId()
                            , cluster.getId())
            );
            if (personInfo.size() > 0)
                person.setPredicate(Float.parseFloat(personInfo.get(0)[0].toString())
                            / (L3 + Integer.parseInt(personInfo.get(0)[1].toString())));
            personDao.update(person);
        }
    }

    @Override
    public void calculate(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            calculate(cluster);
    }
}
