package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import com.IMaylatov.Recommend.webapp.Service.Person.SongUrl;
import com.IMaylatov.Recommend.webapp.Service.Song.SongFilter;
import com.IMaylatov.Recommend.webapp.Service.Song.SongNameUrl;
import com.IMaylatov.Recommend.webapp.Service.Song.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;
    @Autowired
    private PersonDao personDao;


    @RequestMapping("/getSongsByName")
    public @ResponseBody
    List<SongInfo> getSongsByName(@RequestParam("name") String name){
        return songService.getSongByName(name);
    }

    @RequestMapping("/getSongs")
    public @ResponseBody
    List<SongNameUrl> getSongs(@RequestParam("name") String name){
        SongFilter filter = new SongFilter.Builder().setName(name).build();
        return songService.getSongs(filter);
    }

    @RequestMapping("/getSongsByAuthor")
    public @ResponseBody
    List<SongNameUrl> getSongsByAuthor(@RequestParam("userId") long userId,
            @RequestParam("nameAuthor") String nameAuthor){
        SongFilter filter = new SongFilter.Builder().setAuthorName(nameAuthor).build();
        Person person = personDao.find(userId);
        return songService.getSongsByAuthor(person, filter);
    }

    @RequestMapping("/getSongsByGenre")
    public @ResponseBody
    List<SongUrl> getSongsByGenre(@RequestParam("userId") long userId,
                                      @RequestParam("nameGenre") String nameGenre){
        SongFilter filter = new SongFilter.Builder().setGenreName(nameGenre).build();
        Person person = personDao.find(userId);
        return songService.getSongsByGenre(person, filter);
    }
}
