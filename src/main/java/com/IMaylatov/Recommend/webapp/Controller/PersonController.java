package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
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
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDao personDao;

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
}
