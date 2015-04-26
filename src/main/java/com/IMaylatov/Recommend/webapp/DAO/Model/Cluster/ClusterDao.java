package com.IMaylatov.Recommend.webapp.DAO.Model.Cluster;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface ClusterDao extends GenericDao<Cluster, Long> {
    Cluster findWithoutLazy(Long id);
}
