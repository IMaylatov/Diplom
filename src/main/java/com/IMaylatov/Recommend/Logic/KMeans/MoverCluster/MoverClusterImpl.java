package com.IMaylatov.Recommend.Logic.KMeans.MoverCluster;

import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015
 */
@Service("MoverCluster")
public class MoverClusterImpl implements MoverCluster {
    @Autowired
    private DbUtil dbUtil;
    @Autowired
    private SongDao songDao;
    @Autowired
    private ClusterDao clusterDao;

    /**
     * Двигаем центроид. Для этого пройдемся по каждому пользователю из кластера.
     * Для каждого пользователя пройдемся по оценкам. Посчитам кол-во оценок и сумму оценок для каждой песни.
     * Разделим сумму оценок на их количество и получим новую оценку кластера для песни, т.е. новую координаты центра
     */
    @Override
    public boolean move(Cluster cluster) {
        List<Object[]> songsInfo = dbUtil.retrieve(String.format(
                        "Select Song.Id, sum(RatePerson.Value), count(RatePerson.*) from Cluster" +
                                " inner join Person on Cluster.Id = Person.ClusterId and Cluster.Id = %d" +
                                " inner join RatePerson on Person.Id = RatePerson.PersonId" +
                                " inner join Song on Song.Id = RatePerson.SongId" +
                                " group by Song.Id"
                        , cluster.getId())
        );

        boolean isMove = false;

        cluster.setCountRate(0);
        cluster.setSummaRate(0);
        for(Object[] songInfo : songsInfo){
            Song song = songDao.find(Long.parseLong(songInfo[0].toString()));
            int newValue = Integer.parseInt(songInfo[1].toString()) / Integer.parseInt(songInfo[2].toString());
            if(cluster.getRates().containsKey(song))
                if(cluster.getRates().get(song) != newValue)
                    isMove = true;
            cluster.getRates().put(song, newValue);
            cluster.setSummaRate(cluster.getSummaRate() + Integer.parseInt(songInfo[1].toString()));
            cluster.setCountRate(cluster.getCountRate() + Integer.parseInt(songInfo[2].toString()));
        }
        clusterDao.update(cluster);

        return isMove;
    }
}
