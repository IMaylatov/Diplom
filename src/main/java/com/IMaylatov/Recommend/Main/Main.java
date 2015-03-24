package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.DbContext;
import com.IMaylatov.Recommend.Entity.Person;
import com.IMaylatov.Recommend.Entity.Rate;
import com.IMaylatov.Recommend.Entity.Song;
import com.IMaylatov.Recommend.Service.Entity.Person.PersonService;
import com.IMaylatov.Recommend.Service.Entity.Rate.RateService;
import com.IMaylatov.Recommend.Service.Entity.Song.SongService;


/**
 * Created by Liggoriya on 21.03.2015.
 */
public class Main {
    public static void main(String[] args) {
        PersonService personService = DbContext.INSTANCE.getService("PersonService");
        Person person = personService.find(1L);

        SongService songService = DbContext.INSTANCE.getService("SongService");
        Song song = songService.find(1L);

        RateService rateService = DbContext.INSTANCE.getService("RateService");
        Rate rate = rateService.find(new Rate.RatePK(person, song));

        System.out.print("hi");
    }
}
