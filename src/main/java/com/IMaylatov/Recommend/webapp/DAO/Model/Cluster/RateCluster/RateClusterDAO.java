package com.IMaylatov.recommend.webapp.dao.Model.Cluster.RateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAO;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Rate.PairKey.PairKey;
import com.IMaylatov.recommend.webapp.model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.recommend.webapp.model.Song;

/**
 * Интерфейс для работы с сущность RateCluster
 */
public interface RateClusterDAO extends GenericDAO<RateCluster, PairKey<Cluster, Song>> {
    /**
     * Очистить таблицу оценок
     * @return Количество затронутых - удаленных строк
     * @throws org.hibernate.HibernateException
     */
    int deleteAll();
}
