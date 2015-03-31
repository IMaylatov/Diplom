package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.DAO.Model.Rate.RateDAO;
import com.IMaylatov.Recommend.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.DbContext;
import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Model.Rate;
import com.IMaylatov.Recommend.Model.Song;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by Liggoriya on 21.03.2015.
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext instance =
                (new ClassPathXmlApplicationContext(new String[] {"app-context.xml"},true));

        PersonDAO personDAO = (PersonDAO) instance.getBean("PersonDAO");
        RateDAO rateDAO = (RateDAO) instance.getBean("RateDAO");
        SongDAO songDAO = (SongDAO) instance.getBean("SongDAO");

        Person person = new Person();
        Song song = new Song();
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);

        personDAO.save(person);
        songDAO.save(song);
        rateDAO.save(rate);

    }
}
