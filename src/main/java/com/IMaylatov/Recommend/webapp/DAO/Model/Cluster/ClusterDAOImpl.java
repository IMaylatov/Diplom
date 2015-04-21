package com.IMaylatov.recommend.webapp.dao.model.cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.dao.model.person.PersonDao;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("ClusterDAO")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ClusterDaoImpl extends GenericDaoImpl<Cluster, Long> implements ClusterDao {
    @Autowired
    private PersonDao personDAO;

    @Override
    public int deleteAll() {
        List<Cluster> clusters = list();
        for (Cluster cluster : clusters)
            delete(cluster);
        return clusters.size();
    }

    @Override
    public void delete(Cluster cluster){
        for(Person person : cluster.getPersons()) {
            person.setCluster(null);
            personDAO.update(person);
        }
        currentSession().delete(cluster);
    }
}
