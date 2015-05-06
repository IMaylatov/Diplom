package com.IMaylatov.Recommend.webapp.Service.Person;


import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
public interface PersonService {
    List<SongInfo> getStackSongs(Person person);
    void savePerson(String name, String password);
    void addRate(Person person, Song song, int rate);
    PersonInfo getPersonByName(String name);
}
