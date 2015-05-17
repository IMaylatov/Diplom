package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonHistory.PersonHistoryDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson.RatePersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonHistory;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import com.IMaylatov.Recommend.webapp.Service.Person.PersonService;
import com.IMaylatov.Recommend.webapp.Service.Person.SongUrl;
import com.IMaylatov.Recommend.webapp.Service.Song.SongService;
import com.google.gson.JsonObject;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @Autowired
    private SongService songService;
    @Autowired
    private PersonHistoryDao personHistoryDao;
    @Autowired
    private RatePersonDao ratePersonDao;

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
    List<SongUrl> getStackSongs(@RequestParam("userId") long userId) throws IOException {
        Person person = personDao.find(userId);
        List<SongUrl> songsUrl = personService.getStackSongs(person);

        List<SongUrl> result = new ArrayList<>();
        for(SongUrl songUrl : songsUrl)
            result.add(new SongUrl(songUrl.getUrl() + ".mp3"));
        return result;
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

    @RequestMapping("/saveSongInHistory")
    public
    void saveSongInHistory(@RequestParam("userId") long userId, @RequestParam("songUrl") String songUrl){
        Person person = personDao.find(userId);
        Song song = songService.getSongByUrl(songUrl);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        PersonHistory personHistory = new PersonHistory(person.getId(), song.getId(), date);
        personHistoryDao.save(personHistory);
    }

    @RequestMapping("/getRateForSong")
    public @ResponseBody
    String getRateForSong(@RequestParam("userId") long userId, @RequestParam("songUrl") String songUrl){
        Person person = personDao.find(userId);
        Song song = songService.getSongByUrl(songUrl);
        RatePerson ratePerson = ratePersonDao.find(new PairKey<>(person,song));
        if(ratePerson != null)
            return "{rate:" + ratePerson.getValue() + "}";
        return "{rate:0}";
    }
}
