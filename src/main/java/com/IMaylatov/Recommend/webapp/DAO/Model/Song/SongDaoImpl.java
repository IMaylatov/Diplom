package com.IMaylatov.Recommend.webapp.DAO.Model.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("SongDAO")
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {
}
