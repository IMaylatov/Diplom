package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.List;

/**
 * –аспредел€т пользователей по кластерам
 */
public interface SpreadPersonToCluster {
    /**
     * –авномерно распредел€ет пользователей по кластерам
     * @param persons ѕользователи
     * @param k  оличество кластеров
     * @return —писок кластеров, содержащий распределенных пользователей
     */
    List<Cluster> simpleSpread(List<Person> persons, int k);

    /**
     * –аспредел€ет пользователей по кластерам с учетом их рассто€ни€ до кластера по заданной метрике
     * @param clusters  ластера по которым происходит распределние
     * @param persons ѕользователи, которых необходимо распределить
     * @param metric ћетрика, по которой происходит распределение
     * @return List<Person> список распределенных пользователей
     */
    List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric);
}
