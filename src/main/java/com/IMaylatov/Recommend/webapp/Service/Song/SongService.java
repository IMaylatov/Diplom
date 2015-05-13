package com.IMaylatov.Recommend.webapp.Service.Song;

import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.SongInfo;
import com.IMaylatov.Recommend.webapp.Service.Person.SongUrl;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 27.04.2015.
 */
public interface SongService {
    List<SongInfo> getSongByName(String name);
    List<SongNameUrl> getSongs(SongFilter filter);
    List<SongNameUrl> getSongsByAuthor(Person person, SongFilter filter);
    List<SongUrl> getSongsByGenre(Person person, SongFilter filter);
}
