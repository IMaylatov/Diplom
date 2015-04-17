package com.IMaylatov.Recommend.Business.SVD;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface SVD {
    /**
     * Расчитать предикаты для пользователей в k кластерах
     */
    void calculatePredicate(int k);
}
