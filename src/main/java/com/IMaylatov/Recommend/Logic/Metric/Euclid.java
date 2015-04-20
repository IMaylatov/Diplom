package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Rate.HasRates;
import com.IMaylatov.Recommend.Logic.Model.Rate.Rate;

import java.util.Iterator;

/**
 * Евклидова метрика
 */
public class Euclid implements Metric {
    @Override
    public double compare(HasRates person1, HasRates person2) throws IllegalArgumentException {
        double distance = 0;
        boolean isCommonSongs = false;

        Iterator<Rate> rateIterator = person1.iteratorRates();
        while(rateIterator.hasNext()){
            Rate ratePerson1 = rateIterator.next();
            Rate ratePerson2 = person2.getRate(ratePerson1.getSong());
            if (ratePerson2 != null) {
                distance += Math.pow(ratePerson1.getValue() - ratePerson2.getValue(), 2);
                isCommonSongs = true;
            }
        }

        if (!isCommonSongs)
            throw new IllegalArgumentException("У пользователей нет общих оценок");

        return Math.sqrt(distance);
    }
}
