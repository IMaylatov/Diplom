package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInClusterable;
import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.List;

/**
 * –аспредел€т пользователей по кластерам
 */
public interface SpreadPersonInClusterable {
    /**
     * –авномерно распредел€ет пользователей по кластерам
     * @return —писок кластеров, содержащий распределенных пользователей
     */
    List<Cluster> simpleSpread(List<Person> persons, int k, FormingRateInClusterable formingRateInCluster);

    /**
     * –аспредел€ет пользователей по ближайшим кластерам по заданной метрике
     * @return —писок распределенных пользователей
     */
    List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric);
}
