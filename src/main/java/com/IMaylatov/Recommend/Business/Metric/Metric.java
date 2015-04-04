package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Person;

/**
 * Метрика сравнения пользователей
 */
public interface Metric {
    /**
     * Сравнивает пользователей и возвращает расстояние между ними
     * @param person1 Первый пользователь
     * @param person2 Второй пользователь
     * @return Расстояние между пользователями
     */
    double compare(Person person1, Person person2) throws IllegalArgumentException;
}
