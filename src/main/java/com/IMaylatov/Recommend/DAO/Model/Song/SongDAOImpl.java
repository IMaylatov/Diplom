package com.IMaylatov.Recommend.DAO.Model.Song;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Model.Song;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Song
 */
@Repository("SongDAO")
public class SongDAOImpl extends GenericDAOImpl<Song, Long> implements SongDAO {
}