package com.IMaylatov.Recommend.Logic.DAO.Model.Song;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Song;
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