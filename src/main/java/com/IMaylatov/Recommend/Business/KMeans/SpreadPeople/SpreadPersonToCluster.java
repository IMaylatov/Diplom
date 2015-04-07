package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
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
}
