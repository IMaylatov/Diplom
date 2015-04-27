package com.IMaylatov.Recommend.webapp.Service.Song;

import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 27.04.2015.
 */
@Service("SongService")
public class SongServiceImpl implements SongService{
    @Autowired
    private SongInfoDao songInfoDao;

    @Override
    public List<SongInfo> getSongByName(String name) {
        return songInfoDao.list(Restrictions.sqlRestriction(
                String.format("Name like '%%%s%%'", name)));
    }
}
