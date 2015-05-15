package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.Model.AuthorSongGenre.AuthorSong;
import com.IMaylatov.Recommend.webapp.Service.AuthorSong.AuthorSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 14.05.2015.
 */
@Controller
@RequestMapping("/author")
public class AuthorSongController {
    @Autowired
    private AuthorSongService authorSongService;

    @RequestMapping("/getAuthorByName")
    public @ResponseBody
    List<AuthorSong> getAuthorByName(@RequestParam("authorName") String authorName) {
        return authorSongService.getAuthorByName(authorName);
    }
}
