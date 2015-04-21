package com.IMaylatov.Recommend.Logic.KMeans.BuilderRates;

import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Song;
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
    public void build(Cluster cluster) {
        List<Song> songs = songDao.songsInCluster(cluster);

        cluster.setCountRate(0);
        cluster.setSummaRate(0);
        Random random = new Random();
        for(Song song : songs){
            int value = random.nextInt(5) + 1;
            cluster.getRates().put(song, value);
            cluster.setCountRate(cluster.getCountRate() + 1);
            cluster.setSummaRate(cluster.getSummaRate() + value);
        }
        clusterDao.update(cluster);
    }
}
