package com.IMaylatov.Recommend.Logic.DAO.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Класс для работы с сущность Cluster
 */
@Repository("ClusterDAO")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ClusterDAOImpl extends GenericDAOImpl<Cluster, Long> implements ClusterDAO {
    @Override
    public int deleteAll() {
        List<Cluster> clusters = list();
        for (Cluster cluster : clusters)
            delete(cluster);
        return clusters.size();
    }
}
