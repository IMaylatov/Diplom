package com.IMaylatov.Recommend.webapp.Service;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService{
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private SongDao songDao;

    @Override
    public Song getPrioritySong(Person person) {
        List<Song> songs = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("Id in (Select Song.Id" +
                              " from RateCluster" +
                              " inner join Song on Song.Id = RateCluster.SongId" +
                              " inner join Cluster on Cluster.Id = RateCluster.ClusterId and Cluster.Id = %d)",
                        person.getCluster().getId())
        ));

        DealerRate dealerRate = new DealerRateImpl();
        return songs.stream().max(Comparator.comparing(item -> dealerRate.getRateFloat(person, item))).get();
    }
}
