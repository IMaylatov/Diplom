package com.IMaylatov.Recommend.Business.Metric;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate;

import java.util.HashSet;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

/**
 * Евклидова метрика
 */
public class Euclid implements Metric {
    @Override
    public double compare(Person person1, Person person2) throws IllegalArgumentException {
        double distance = 0;
        boolean isCommonSongs = false;

        for (Rate ratePerson1 : person1.getRateList()) {
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
