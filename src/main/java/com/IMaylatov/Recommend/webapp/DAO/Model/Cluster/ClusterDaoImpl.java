package com.IMaylatov.Recommend.webapp.DAO.Model.Cluster;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("ClusterDao")
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

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Cluster findWithoutLazy(Long id) {
        Cluster cluster = (Cluster) currentSession().get(typeEntity, id);
        Hibernate.initialize(cluster.getRates());
        return cluster;
    }
}
