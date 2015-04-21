package com.IMaylatov.Recommend.webapp.DbUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */
@Repository("dbUtil")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class DbUtilImpl implements DbUtil {
    @Autowired
    private SessionFactory sessionFactory;

    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int execute(String query){
        return currentSession().createSQLQuery(query).executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> List<V> retrieve(String query){
        return currentSession().createSQLQuery(query).list();
    }
}
