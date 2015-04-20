package com.IMaylatov.recommend.webapp.dao.Model.Song;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Song
 */
@Repository("SongDAO")
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {
}