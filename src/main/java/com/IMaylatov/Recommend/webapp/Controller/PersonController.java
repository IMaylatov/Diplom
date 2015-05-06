package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import com.IMaylatov.Recommend.webapp.Service.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private SongDao songDao;

    @RequestMapping("/getSongsForUser")
    public @ResponseBody
    List<Long> getSongs(@RequestParam("userId") long userId){
        Person person = personDao.findWithoutLazy(userId);
        List<Long> songsId = new ArrayList<>();
        person.getRates().keySet().stream().forEach(song -> songsId.add(song.getId()));
        return songsId;
    }

    @RequestMapping("/getStackSongsForUser")
    public @ResponseBody
    List<SongInfo> getStackSongs(@RequestParam("userId") long userId){
        Person person = personDao.find(userId);
        return personService.getStackSongs(person);
    }

    @RequestMapping("/personAddRate")
    public @ResponseBody
    String addRate(@RequestParam("userId") long userId, @RequestParam("songId") long songId,
                   @RequestParam("rate") int rate){
        Person person = personDao.findWithoutLazy(userId);
        Song song = songDao.find(songId);

        personService.addRate(person, song, 3);
        return "OK";
    }

    @RequestMapping("/getId")
    public @ResponseBody
    String getId(@RequestParam("username") String username){
        PersonInfo person = personService.getPersonByName(username);
        if(person != null)
            return String.format("{personId: %d}", person.getPerson().getId());
        return "{personId: 0}";
    }
}
