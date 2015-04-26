package com.IMaylatov.Recommend.webapp.DAO.Model.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface SongDao extends GenericDao<Song, Long> {
    List<Song> listWithoutLazy();
    List<Song> listWithoutLazy(Criterion criterion);
    Song findWithoutLazy(Long id);
}