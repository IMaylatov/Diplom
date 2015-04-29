package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
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


    @RequestMapping("/getSongsByName")
    public @ResponseBody
    List<SongInfo> getSongs(@RequestParam("name") String name){
        return songService.getSongByName(name);
    }
}
