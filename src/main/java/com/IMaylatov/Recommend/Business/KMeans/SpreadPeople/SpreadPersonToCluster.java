package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.List;

/**
 * Распределят пользователей по кластерам
 */
public interface SpreadPersonToCluster {
    /**
     * Равномерно распределяет пользователей по кластерам
     * @param persons Пользователи
     * @param k Количество кластеров
     * @return Список кластеров, содержащий распределенных пользователей
     */
    List<Cluster> simpleSpread(List<Person> persons, int k);
}
