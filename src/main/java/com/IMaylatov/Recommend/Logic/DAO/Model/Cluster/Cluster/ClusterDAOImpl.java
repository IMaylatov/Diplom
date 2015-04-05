package com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import org.springframework.stereotype.Repository;

/**
 * Класс для работы с сущность Cluster
 */
@Repository("ClusterDAO")
public class ClusterDAOImpl extends GenericDAOImpl<Cluster, Long> implements ClusterDAO {
}
