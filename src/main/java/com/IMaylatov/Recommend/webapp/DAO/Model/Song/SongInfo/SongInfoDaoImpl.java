package com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Repository("SongInfoDao")
public class SongInfoDaoImpl extends GenericDaoImpl<SongInfo, Long> implements SongInfoDao {
}
