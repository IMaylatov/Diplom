package com.IMaylatov.Recommend.webapp.Service.Song;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import com.IMaylatov.Recommend.webapp.Service.Person.SongUrl;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 27.04.2015.
 */
@Service("SongService")
public class SongServiceImpl implements SongService{
    @Autowired
    private SongInfoDao songInfoDao;
    @Autowired
    private SongDao songDao;

    @Override
    public List<SongInfo> getSongByName(String name) {
        return songInfoDao.list(Restrictions.sqlRestriction(
                String.format("Name like '%%%s%%'", name)));
    }

    @Override
    public List<SongNameUrl> getSongs(SongFilter filter) {
        List<SongInfo> songsInfo = songInfoDao.list(Restrictions.sqlRestriction(
                String.format("Name like '%s%%'", filter.getName())));

        List<SongNameUrl> songNameUrls = new ArrayList<>();

        for(SongInfo info : songsInfo)
            songNameUrls.add(new SongNameUrl(info.getName(), info.getUrl()));

        return songNameUrls;
    }

    @Override
    public List<SongNameUrl> getSongsByAuthor(Person person, SongFilter filter) {
        List<Song> songs = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("Id in (Select SongId from SongInfo where" +
                        " AuthorSongId in (Select id from AuthorSong" +
                        " where name like replace('%s', '_',' ')))", filter.getAuthorName())));
        DealerRate dealerRate = new DealerRateImpl();
        songs.sort((t1, t2) -> Float.compare(
                dealerRate.getRateFloat(person, t2),
                dealerRate.getRateFloat(person, t1)));

        StringBuilder songsId = new StringBuilder("(");
        for(Song song : songs) {
            String s = song.getId() + ",";
            songsId.append(s);
        }
        songsId.deleteCharAt(songsId.length() - 1);
        songsId.append(")");

        List<SongInfo> songsInfo = songInfoDao.list(Restrictions.sqlRestriction(
                String.format("SongId in %s", songsId)));

        List<SongNameUrl> songNameUrls = new ArrayList<>();

        for(SongInfo info : songsInfo)
            songNameUrls.add(new SongNameUrl(info.getName(), info.getUrl()));

        return songNameUrls;
    }

    @Override
    public List<SongUrl> getSongsByGenre(Person person, SongFilter filter) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Song> songs = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("Id in (Select SongId from SongInfo where" +
                        " AuthorSongId in (Select distinct AuthorSongID from AuthorSongGenre" +
                        " where GenreId in (Select Id from Genre where name like '%s')))" +
                        " and Id not in (Select distinct SongId from PersonHistory" +
                        " where Date > '%s')",
                        filter.getGenreName(),
                        dateFormat.format(calendar.getTime()))));

        DealerRate dealerRate = new DealerRateImpl();
        songs.sort((t1, t2) -> Float.compare(
                dealerRate.getRateFloat(person, t2),
                dealerRate.getRateFloat(person, t1)));

        Random random = new Random();
        double average = random.nextGaussian() * 10;
        final double gauss;
        if(average > 5)
            gauss = 10 - average;
        else
            gauss = average;
        songs.sort((t1, t2) -> Float.compare(
                Math.abs(dealerRate.getRateFloat(person, t2) - (float) gauss),
                Math.abs(dealerRate.getRateFloat(person, t1) - (float) gauss)));

        List<Song> topSongs = songs.size() > 5 ? songs.subList(0, 5) : songs;

        StringBuilder songsId = new StringBuilder("(");
        for(Song song : topSongs) {
            String s = song.getId() + ",";
            songsId.append(s);
        }
        songsId.deleteCharAt(songsId.length() - 1);
        songsId.append(")");

        List<SongInfo> songsInfo = songInfoDao.list(Restrictions.sqlRestriction(
                String.format("SongId in %s", songsId)));

        List<SongUrl> songsUrl = new ArrayList<>();
        for(SongInfo songInfo : songsInfo){
            songsUrl.add(new SongUrl(songInfo.getUrl()));
        }

        return songsUrl;
    }
}
