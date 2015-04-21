package com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate;


import com.IMaylatov.Recommend.webapp.Model.Cluster;

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
