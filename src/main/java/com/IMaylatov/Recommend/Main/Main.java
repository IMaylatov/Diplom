package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.DAO.DbContext;
import com.IMaylatov.Recommend.DAO.Entity.Person.PersonDAO;
import com.IMaylatov.Recommend.DAO.Entity.Rate.RateDAO;
import com.IMaylatov.Recommend.DAO.Entity.Song.SongDAO;
import com.IMaylatov.Recommend.Entity.Person;
import com.IMaylatov.Recommend.Entity.Rate;
import com.IMaylatov.Recommend.Entity.Song;

/**
 * Created by Liggoriya on 21.03.2015.
 */
public class Main {
    public static void main(String[] args) {
//        PersonDAO personDAO = (PersonDAO) DbContext.getInstance().getBean("PersonDAO");
//        Person person = personDAO.getPersonWithoutLazy(1);
//        Person person1 = personDAO.find(1l);
//
//        SongDAO songDAO = (SongDAO) DbContext.getInstance().getBean("SongDAO");
//        Song song = songDAO.getPersonWithoutLazy(1);
//        Song song1 = songDAO.find(1l);
//
//        RateDAO rateDAO = (RateDAO) DbContext.getInstance().getBean("RateDAO");
//        Rate rate = rateDAO.find(new Rate.RatePK(person1, song1));


        PersonDAO personDAO = DbContext.INSTANCE.getDAO("PersonDAO");
        Person person = personDAO.getPersonWithoutLazy(1);

        SongDAO songDAO = DbContext.INSTANCE.getDAO("SongDAO");
        Song song = songDAO.getPersonWithoutLazy(1);
        System.out.print("hi");
    }
}
