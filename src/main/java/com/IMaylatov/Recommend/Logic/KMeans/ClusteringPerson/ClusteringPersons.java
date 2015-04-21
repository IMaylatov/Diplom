package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Logic.Metric.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */

public interface ClusteringPersons {
    /**
     * Создать k кластеров и распределить по ним людей в определенной метрике
     */
    void clustering(int k, Metric metric);
}
