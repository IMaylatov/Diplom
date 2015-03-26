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
        Person person = personDAO.find(6L);

        SongDAO songDAO = DbContext.INSTANCE.getDAO("SongDAO");
        Song song = songDAO.find(1L);

        RateDAO rateDAO = DbContext.INSTANCE.getDAO("RateDAO");
        Rate rate = rateDAO.find(new Rate.RatePK(person, song));

        System.out.println();
    }
}
