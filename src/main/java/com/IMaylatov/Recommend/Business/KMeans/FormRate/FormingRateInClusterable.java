package com.IMaylatov.Recommend.Business.KMeans.FormRate;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Формирует оценки в кластере
 */
public interface FormingRateInClusterable {
    /**
     * В кластере формирует оценки. Для каждой песни, прослушанной пользователем в кластере, выставляет оценку
     */
    void form(Cluster cluster);
}
