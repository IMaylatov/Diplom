package com.IMaylatov.Recommend.Logic.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 12.04.2015.
 */

/**
 * ќценщик и песн€
 */
public interface Rate<T, S extends Song> {
    T getAppraiser();
    S getSong();
    int getValue();
    void setValue(int value);
}
