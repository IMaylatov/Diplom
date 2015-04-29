package com.IMaylatov.Recommend.webapp.Service.Person;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
@Service("PersonService")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private SongDao songDao;
    @Autowired
    private SongInfoDao songInfoDao;

    @Override
    public List<SongInfo> getStackSongs(Person person) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Song> songs = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("Id in " +
                                "(Select distinct SongId from RateCluster" +
                                " where ClusterId = %d and SongId not in" +
                                " (Select distinct SongId from PersonHistory" +
                                " where Date > '%s'))",
                        person.getCluster().getId(),
                        dateFormat.format(calendar.getTime()))));

        DealerRate dealerRate = new DealerRateImpl();
        songs.sort((t1, t2) -> Float.compare(
                dealerRate.getRateFloat(person, t2),
                dealerRate.getRateFloat(person, t1)));

        List<Song> topSongs = songs.subList(0, 7);
        StringBuilder songsId = new StringBuilder("(");
        for(Song song : topSongs) {
            String s = song.getId() + ",";
            songsId.append(s);
        }
        songsId.deleteCharAt(songsId.length() - 1);
        songsId.append(")");

        List<SongInfo> songsInfo = songInfoDao.list(Restrictions.sqlRestriction(
                String.format("SongId in %s", songsId)));
        return songsInfo;
    }
}
