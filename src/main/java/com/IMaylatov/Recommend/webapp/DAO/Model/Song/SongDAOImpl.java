package com.IMaylatov.recommend.webapp.dao.model.song;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.stereotype.Repository;

@Repository("SongDAO")
public class SongDaoImpl extends GenericDaoImpl<Song, Long> implements SongDao {
}