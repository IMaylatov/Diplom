package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */


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
    double compare(Pearson person1, Pearson person2) throws IllegalArgumentException;
}
