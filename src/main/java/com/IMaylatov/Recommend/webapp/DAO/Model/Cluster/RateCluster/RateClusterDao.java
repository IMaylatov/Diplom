package com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface RateClusterDao extends GenericDao<RateCluster, PairKey<Cluster, Song>> {
}

