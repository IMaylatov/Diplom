package com.IMaylatov.Recommend.Logic.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 12.04.2015.
 */
public interface AppraiserSongable<T, S extends Song> {
    /**
     * �������� �������� �����
     */
    T getAppraiser();

    /**
     * �������� �����
     */
    S getSong();
}
