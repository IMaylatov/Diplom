package com.IMaylatov.Recommend.Logic.Service;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService{
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;

    @Override
    public Song getPrioritySong(Person person) {
        Cluster cluster = person.getCluster();
        return cluster.getListRates().stream().
                max(Comparator.comparing(item -> item.getSong().getPredicate(cluster).getValue()))
                .get().getSong();
    }
}
