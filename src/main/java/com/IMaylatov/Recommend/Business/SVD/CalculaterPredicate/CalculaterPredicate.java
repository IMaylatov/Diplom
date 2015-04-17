package com.IMaylatov.Recommend.Business.SVD.CalculaterPredicate;

import com.IMaylatov.Recommend.Logic.Model.Cluster;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public interface CalculaterPredicate {
    /**
     * Расчитывает предикаты для пользователей и песен в кластере
     */
    void calculate(Cluster cluster);
    void calculate(List<Cluster> clusters);
}
