package com.IMaylatov.recommend.webapp.dao.model.cluster.rateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.RateCluster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("RateClusterDao")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class RateClusterDaoImpl extends GenericDaoImpl<RateCluster, PairKey<Cluster, Song>> implements RateClusterDao  {
    @Override
    public int deleteAll() {
        return currentSession().createQuery("delete from rateCluster").executeUpdate();
    }
}
