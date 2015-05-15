package com.IMaylatov.Recommend.webapp.Service.AuthorSong;

import com.IMaylatov.Recommend.webapp.Model.AuthorSongGenre.AuthorSong;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 14.05.2015.
 */
public interface AuthorSongService {
    List<AuthorSong> getAuthorByName(String authorName);
}
