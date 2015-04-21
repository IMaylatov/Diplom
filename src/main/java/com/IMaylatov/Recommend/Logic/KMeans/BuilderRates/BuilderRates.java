package com.IMaylatov.Recommend.Logic.KMeans.BuilderRates;

import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

public interface BuilderRates {
    /**
     * В кластере формирует оценки. Для каждой песни, прослушанной пользователем в кластере, выставляет оценку
     */
    void build(Cluster cluster);
}
