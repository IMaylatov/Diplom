package com.IMaylatov.Recommend.Business.Metric;

import com.IMaylatov.Recommend.Logic.Model.Rate.RatePerson;
import java.util.Iterator;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

/**
 * Евклидова метрика
 */
public class Euclid implements Metric {
    @Override
    public double compare(Pearson person1, Pearson person2) throws IllegalArgumentException {
        double distance = 0;
        boolean isCommonSongs = false;

        //Iterator<RatePerson> ratePersonIterator = person1.getRateIterator();
//        while(ratePersonIterator.hasNext()){
//            RatePerson ratePerson1 = ratePersonIterator.next();
//            RatePerson ratePerson2 = person2.getRate(ratePerson1.getK2());
//            if (ratePerson2 != null) {
//                distance += Math.pow(ratePerson1.getValue() - ratePerson2.getValue(), 2);
//                isCommonSongs = true;
//            }
        //}

        if (!isCommonSongs)
            throw new IllegalArgumentException("У пользователей нет общих оценок");

        return Math.sqrt(distance);
    }
}
