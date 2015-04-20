package com.IMaylatov.recommend.webapp.Service;

import com.IMaylatov.recommend.webapp.dao.Model.Person.PersonDAO;
import com.IMaylatov.recommend.webapp.dao.Model.Song.SongDAO;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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
        return cluster.getRates().stream().
                max(Comparator.comparing(item -> item.getSong().getPredicate(cluster).getValue()))
                .get().getSong();
    }
}
