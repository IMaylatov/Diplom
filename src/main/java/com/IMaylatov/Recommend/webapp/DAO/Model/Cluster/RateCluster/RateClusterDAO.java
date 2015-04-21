package com.IMaylatov.recommend.webapp.dao.model.cluster.rateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.generic.GenericDao;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.RateCluster;

public interface RateClusterDao extends GenericDao<RateCluster, PairKey<Cluster, Song>> {
    /**
     * Очистить таблицу оценок
     */
    int deleteAll();
}
