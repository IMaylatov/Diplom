package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.BuilderRatesable;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
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
    void spread(int k);

    /**
     * Устанавливает метрику
     */
    ClusteringPersons setMetric(Metric metric);

    /**
     * Устанавливает класс, который распределяет пользователей по кластерам
     */
    ClusteringPersons setSpread(SpreadPersonInClusterable spread);

    /**
     * Устанавливает класс, который формирует кластерные оценки - центроид
     */
    ClusteringPersons setFormingRate(BuilderRatesable formingRate);

    /**
     * Устанавливает класс, который двигает центроид в центр масс пользователей, принадлежащих кластеру
     */
    ClusteringPersons setMover(MoverCluster mover);
}
