package com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("RateClusterDao")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class RateClusterDaoImpl extends GenericDaoImpl<RateCluster, PairKey<Cluster, Song>> implements RateClusterDao {
}

