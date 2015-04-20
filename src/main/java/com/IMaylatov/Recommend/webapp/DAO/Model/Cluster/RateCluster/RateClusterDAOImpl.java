package com.IMaylatov.recommend.webapp.dao.Model.Cluster.RateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAOImpl;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Rate.PairKey.PairKey;
import com.IMaylatov.recommend.webapp.model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс для работы с сущностью RateCluster
 */
@Repository("RateClusterDAO")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class RateClusterDAOImpl extends GenericDAOImpl<RateCluster, PairKey<Cluster, Song>> implements RateClusterDAO  {
    @Override
    public int deleteAll() {
        return currentSession().createQuery("delete from RateCluster").executeUpdate();
    }
}
