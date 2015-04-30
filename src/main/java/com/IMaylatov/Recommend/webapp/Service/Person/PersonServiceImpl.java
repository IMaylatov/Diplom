package com.IMaylatov.Recommend.webapp.Service.Person;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonInfo.PersonInfoDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonRoles.PersonRolesDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongInfo.SongInfoDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonRoles;
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
    @Autowired
    private PersonDao personDao;
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private PersonInfoDao personInfoDao;
    @Autowired
    private PersonRolesDao personRolesDao;

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
}
