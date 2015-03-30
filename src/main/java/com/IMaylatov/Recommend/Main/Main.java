package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.DAO.Model.Rate.RateDAO;
import com.IMaylatov.Recommend.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.DbContext;
import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Model.Rate;
import com.IMaylatov.Recommend.Model.Song;


/**
 * Created by Liggoriya on 21.03.2015.
 */
public class Main {
    public static void main(String[] args) {
        PersonDAO personDAO = DbContext.INSTANCE.getDAO("PersonDAO");
        RateDAO rateDAO = DbContext.INSTANCE.getDAO("RateDAO");
        SongDAO songDAO = DbContext.INSTANCE.getDAO("SongDAO");

        Song song = new Song();
        Person person = new Person();
        personDAO.save(person);
        //songDAO.save(song);
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);

        System.out.println();
    }
}
