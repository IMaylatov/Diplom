package com.IMaylatov.Recommend.webapp.DAO.Model.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("SongDao")
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {
    @Override
    public List<Song> songsInCluster(Cluster cluster) {
        return list(Restrictions.sqlRestriction(
                String.format(
                        "Id in (Select Song.Id from Cluster" +
                                " inner join Person on Cluster.Id = Person.ClusterId and Cluster.Id = %d" +
                                " inner join RatePerson on Person.Id = RatePerson.PersonId" +
                                " inner join Song on Song.Id = RatePerson.SongId" +
                                " group by Song.Id)"
                        , cluster.getId())
        ));
    }

    @Override
    public Song loadPredicates(Song song) {
        Song songFind = find(song.getId());
        Hibernate.initialize(songFind.getPredicates());
        song.setPredicates(songFind.getPredicates());
        return song;
    }
}
