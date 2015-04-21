package com.IMaylatov.recommend.webapp.dao.model.cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.generic.GenericDao;
import com.IMaylatov.recommend.webapp.model.Cluster;

public interface ClusterDao extends GenericDao<Cluster, Long> {
    /**
     * ќчистить таблицу кластеров
     * @return  оличество затронутых строк - удаленных кластеров
     */
    int deleteAll();
}
