package com.IMaylatov.Recommend.webapp.Model.Rate;

import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import java.util.Map;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface Ratesable {
    Map<Song, Integer> getRates();
}
