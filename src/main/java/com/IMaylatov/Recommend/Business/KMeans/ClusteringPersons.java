package com.IMaylatov.Recommend.Business.KMeans;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInClusterable;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverClusterable;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInClusterable;
import com.IMaylatov.Recommend.Business.Metric.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */

public interface ClusteringPersons {
    /**
     * Создать k кластеров и распределить по ним людей в определенной метрике
     */
    void spread(int k,  Metric metric,
                SpreadPersonInClusterable spread,
                FormingRateInClusterable formingRateInCluster,
                MoverClusterable moverCluster);
}
