package com.IMaylatov.Recommend.webapp.DAO.Model.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("SongDao")
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {
    @Override
    public Song findWithoutLazy(Long id) {
        Song song = (Song) currentSession().get(typeEntity, id);
        Hibernate.initialize(song.getPredicates());
        return song;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Song> listWithoutLazy(){
        List<Song> songs = currentSession().createCriteria(typeEntity).list();
        for(Song song : songs)
            Hibernate.initialize(song.getPredicates());
        return songs;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Song> listWithoutLazy(Criterion criterion) {
        List<Song> songs = currentSession().createCriteria(typeEntity).add(criterion).list();
        for(Song song : songs)
            Hibernate.initialize(song.getPredicates());
        return songs;
    }
}
