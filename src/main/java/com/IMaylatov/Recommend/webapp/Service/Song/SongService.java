package com.IMaylatov.Recommend.webapp.Service.Song;

import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 27.04.2015.
 */
public interface SongService {
    List<SongInfo> getSongByName(String name);
}
