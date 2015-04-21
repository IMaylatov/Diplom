package com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;

import java.util.List;

/**
 * –аспредел€т пользователей по кластерам
 */
public interface SpreadPersonInClusterable {
    /**
     * –авномерно распредел€ет пользователей по кластерам
     * @return —писок кластеров, содержащий распределенных пользователей
     */
    List<Cluster> simpleSpread(List<Person> persons, int k, BuilderRatesable formingRateInCluster);

    /**
     * –аспредел€ет пользователей по ближайшим кластерам по заданной метрике
     * @return —писок распределенных пользователей
     */
   //List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric);
}
