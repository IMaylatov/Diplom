package com.IMaylatov.Recommend.Logic.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Song;

import java.util.Iterator;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */
public interface HasRates<T extends Rate> {
    boolean addRate(Song song, int value);
    T getRate(Song song);
    boolean removeRate(Song song);
    Iterator<T> iteratorRates();
}
