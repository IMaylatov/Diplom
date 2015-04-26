package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Controller
public class SongController {
    @Autowired
    private SongDao songDao;

    @RequestMapping("/getSongsForUser")
    public @ResponseBody
    List<Long> getSongs(@RequestParam("userId") long userId){
        List<Song> songs = songDao.list(Restrictions.sqlRestriction(
                String.format("Id in (Select RatePerson.SongId from RatePerson" +
                                    " inner join Person on Person.Id = RatePerson.PersonId" +
                                    " and Person.Id = %d)",
                        userId)));

        List<Long> songsId = new ArrayList<>();
        songs.stream().forEach(song -> songsId.add(song.getId()));

        return songsId;
    }
}
