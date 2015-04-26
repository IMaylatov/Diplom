package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 22.04.2015
 */
public class RMSEMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        PersonDao personDao = (PersonDao) context.getBean("PersonDao");
        SongDao songDao = (SongDao) context.getBean("SongDao");

        double error = 0;
        int countRate = 0;
        DealerRate dealerRate = new DealerRateImpl();
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream("data/testRate.dat");
            Scanner scanner = new Scanner(stream)){
            Set<Long> personIdSet = new HashSet<>();
            Set<Long> songIdSet = new HashSet<>();
            Map<PersonSong, Integer> values = new HashMap<>();

            while (scanner.hasNext()) {
                String[] rateInfo = scanner.nextLine().split(" ");
                Long personId = Long.parseLong(rateInfo[0]);
                Long songId = Long.parseLong(rateInfo[1]);
                Integer value = Integer.parseInt(rateInfo[2]);

                personIdSet.add(personId);
                songIdSet.add(songId);
                values.put(new PersonSong(personId, songId), value);
            }

            StringJoiner stringJoiner = new StringJoiner(",");
            for(Long songId : songIdSet)
                stringJoiner.add(songId.toString());
            String songIdString = "(" + stringJoiner.toString() + ")";
            List<Song> songList = songDao.listWithoutLazy(Restrictions.sqlRestriction(
                    "Id in " + songIdString));
            Map<Long, Song> songMap = new HashMap<>();
            for(Song song : songList)
                songMap.put(song.getId(), song);

            stringJoiner = new StringJoiner(",");
            for(Long perId : personIdSet)
                stringJoiner.add(perId.toString());
            String personIdString = "(" + stringJoiner.toString() + ")";
            List<Person> personList = personDao.list(Restrictions.sqlRestriction(
                    "this_.Id in " + personIdString));
            Map<Long, Person> personMap = new HashMap<>();
            for(Person person : personList)
                personMap.put(person.getId(), person);

            for(Map.Entry<PersonSong, Integer> rate : values.entrySet()){
                Person person = personMap.get(rate.getKey().personId);
                Song song = songMap.get(rate.getKey().songId);

                float extendedRate = dealerRate.getRate(person, song);
                error += Math.pow(rate.getValue() - extendedRate, 2);
                countRate++;
            }

            error /= countRate;
            System.out.println("RMSE = " + error);
        }catch (IOException e){
        }
    }

    private static class PersonSong{
        public Long personId;
        public Long songId;

        public PersonSong(Long personId, Long songId) {
            this.personId = personId;
            this.songId = songId;
        }
    }
}
