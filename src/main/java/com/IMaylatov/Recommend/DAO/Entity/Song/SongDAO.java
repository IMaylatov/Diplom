package com.IMaylatov.Recommend.DAO.Entity.Song;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Entity.Song;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Интерфейс для работы с сущностью Song
 */
public interface SongDAO extends GenericDAO<Song, Long> {
    public Song getPersonWithoutLazy(long id);
}