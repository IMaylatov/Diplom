package com.IMaylatov.Recommend.DAO.Entity.Song;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Entity.Song;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Song
 */
@Repository("SongDAO")
public class SongDAOImpl extends GenericDAOImpl<Song, Long> implements SongDAO {
}