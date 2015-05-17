package com.IMaylatov.Recommend.webapp.Service.Person;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonInfo.PersonInfoDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonRoles.PersonRolesDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson.RatePersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonRoles;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private PersonRolesDao personRolesDao;
    @Autowired
    private RatePersonDao ratePersonDao;

    @Override
    public List<SongUrl> getStackSongs(Person person) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, -2);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Song> songs = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("Id not in" +
                                " (Select distinct SongId from PersonHistory" +
                                " where Date > '%s')" +
                                " and Id not in" +
                                " (select SongId from BlackList where PersonId = %d)",
                        dateFormat.format(calendar.getTime()),
                        person.getId())));
        if(songs.size() == 0)
            songs = songDao.listWithoutLazy();

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

    @Override
    public void savePerson(String name, String password) {
        List<Cluster> clusters = clusterDao.list();
        Cluster cluster = clusters.size() > 0 ? clusters.get(0) : null;
        Person person = new Person();
        person.setCluster(cluster);
        personDao.save(person);
        personDao.flush();

        PersonInfo personInfo = personInfoDao.find(person.getId());
        personInfo.setName(name);
        personInfo.setPassword(password);
        personInfo.setEnabled(true);

        personInfo.getPersonRoles().add(new PersonRoles(new PersonRoles.PairKey(personInfo, "ROLE_USER")));

        personInfoDao.update(personInfo);
        personInfoDao.flush();
    }

    @Override
    public void addRate(Person person, Song song, int rate) {
        if ((person == null) || (song == null))
            throw new IllegalArgumentException("Null pointer: person=" + person + "; song=" + song);

        person.getRates().put(song, rate);
        personDao.update(person);
    }

    @Override
    public PersonInfo getPersonByName(String name) {
        List<PersonInfo> persons = personInfoDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("name like '%s'", name)));
        if(persons.size() == 1)
            return persons.get(0);
        return null;
    }

    @Override
    public List<SongUrlRate> getSongsUserMoreRate(long userId, int rate) {
        List<RatePerson> ratePersons = ratePersonDao.listWithoutLazy(Restrictions.sqlRestriction(
                String.format("PersonId = %d and value >= %d",
                        userId,
                        rate)));

        List<SongUrlRate> songUrlRates = new ArrayList<>();
        for(RatePerson ratePerson : ratePersons)
                songUrlRates.add(new SongUrlRate(
                                ratePerson.getSong().getSongInfo().getName() + "",
                                ratePerson.getSong().getSongInfo().getUrl() + ".mp3",
                                ratePerson.getValue()));

        return songUrlRates;
    }
}
