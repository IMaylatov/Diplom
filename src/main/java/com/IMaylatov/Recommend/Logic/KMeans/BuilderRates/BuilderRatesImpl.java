package com.IMaylatov.Recommend.Logic.KMeans.BuilderRates;

import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@Service("BuilderRates")
public class BuilderRatesImpl implements BuilderRates {
    @Autowired
    private DbUtil dbUtil;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private SongDao songDao;

    @Override
    public void form(Cluster cluster) {
        List<Song> songs = songDao.list(Restrictions.sqlRestriction(
                String.format(
                        "Id in (Select Song.Id from Cluster" +
                                " inner join Person on Cluster.Id = Person.ClusterId and Cluster.Id = %d" +
                                " inner join RatePerson on Person.Id = RatePerson.PersonId" +
                                " inner join Song on Song.Id = RatePerson.SongId" +
                                " group by Song.Id)"
                        , cluster.getId())
        ));

        for(Song song : songs){
            cluster.getRates().put(song, 3);
        }
        clusterDao.save(cluster);
    }
}
