package com.IMaylatov.Recommend.Logic.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

import com.IMaylatov.Recommend.webapp.Model.Rate.Ratesable;
import com.IMaylatov.Recommend.webapp.Model.Song;

import java.util.Map.Entry;

/**
 * Евклидова метрика
 */
public class Euclid implements Metric {
    @Override
    public double compare(Ratesable person1, Ratesable person2){
        double distance = 0;
        boolean isCommonSongs = false;

        for(Entry<Song, Integer> rate1 : person1.getRates().entrySet())
            if (person2.getRates().containsKey(rate1.getKey())) {
                distance += Math.pow(rate1.getValue() - person2.getRates().get(rate1.getKey()), 2);
                isCommonSongs = true;
            }

        if (!isCommonSongs)
            return Double.MAX_VALUE;

        return Math.sqrt(distance);
    }
}
