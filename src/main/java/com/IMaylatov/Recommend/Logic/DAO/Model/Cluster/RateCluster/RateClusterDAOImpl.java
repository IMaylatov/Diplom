package com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Cluster.RateCluster;
import org.springframework.stereotype.Repository;

/**
 * Класс для работы с сущностью RateCluster
 */
@Repository("RateClusterDAO")
public class RateClusterDAOImpl extends GenericDAOImpl<RateCluster, RateCluster.PairKey> implements RateClusterDAO  {
}
