package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Genre.GenreDao;
import com.IMaylatov.Recommend.webapp.Model.AuthorSongGenre.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 14.05.2015.
 */
@Controller
@RequestMapping("/genre")
public class GenreController {
    @Autowired
    private GenreDao genreDao;

    @RequestMapping("/getAllGenre")
    public @ResponseBody
    List<GenreInfo> getAllGenre(){
        List<Genre> genres = genreDao.list();
        List<GenreInfo> genreInfos = new ArrayList<>();
        for(Genre genre : genres)
            genreInfos.add(new GenreInfo(genre.getName()));
        return genreInfos;
    }

    private class GenreInfo{
        private String name;

        public String getName() {
            return name;
        }

        public GenreInfo(String name) {
            this.name = name;
        }
    }
}
