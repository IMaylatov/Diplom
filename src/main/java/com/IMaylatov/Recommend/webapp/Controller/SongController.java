package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonHistory.PersonHistoryDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Controller
public class SongController {
    @Autowired
    private SongDao songDao;
    @Autowired
    private SongInfoDao songInfoDao;
    @Autowired
    private PersonDao personDao;

    @RequestMapping("/getSongsForUser")
    public @ResponseBody
    List<Long> getSongs(@RequestParam("userId") long userId){
        List<Song> songs = songDao.list(Restrictions.sqlRestriction(
                String.format("Id in (Select RatePerson.SongId from RatePerson" +
                                    " inner join Person on Person.Id = RatePerson.PersonId" +
                                    " and Person.Id = %d)",
                        userId)));

        List<Long> songsId = new ArrayList<>();
        songs.stream().forEach(song -> songsId.add(song.getId()));

        return songsId;
    }

    @RequestMapping("/getSongsByName")
    public @ResponseBody
    List<SongInfo> getSongs(@RequestParam("name") String name){
        List<SongInfo> songs = songInfoDao.list(Restrictions.sqlRestriction(
                String .format("Name like '%%%s%%'", name)));
        return songs;
    }

    @RequestMapping("/getStackSongsForUser")
    public @ResponseBody
    List<SongInfo> getStackSongs(@RequestParam("userId") long userId){
        Person person = personDao.find(userId);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Song> songs = songDao.list(Restrictions.sqlRestriction(
                String.format("Id in " +
                                "(Select distinct SongId from RateCluster" +
                                " where ClusterId = %d and SongId not in" +
                                " (Select distinct SongId from PersonHistory" +
                                " where Date > '%s'))",
                        person.getCluster().getId(),
                        dateFormat.format(calendar.getTime()))));

        DealerRate dealerRate = new DealerRateImpl();
        songs.sort((t1, t2) -> Float.compare(
                dealerRate.getRateFloat(person, t1),
                dealerRate.getRateFloat(person, t2)));

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
