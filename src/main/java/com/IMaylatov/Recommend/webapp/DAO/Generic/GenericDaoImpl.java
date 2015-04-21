package com.IMaylatov.Recommend.webapp.DAO.Generic;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public abstract class GenericDaoImpl<T, K extends Serializable> implements GenericDao<T, K> {
    //region field
    /**
     * Фабрика сессий из контейнера Spring
     */
    @Autowired
    private SessionFactory sessionFactory;
    /**
     * Тип сущности
     */
    protected Class<T> typeEntity;
    //endregion

    //region Constructor
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        typeEntity = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    //endregion

    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        currentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        currentSession().delete(entity);
    }

    @Override
    public void flush(){
        currentSession().flush();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T find(K id) {
        return (T) currentSession().get(typeEntity, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> list() {
        return currentSession().createCriteria(typeEntity).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list(Criterion criterion) {
        return currentSession().createCriteria(typeEntity).add(criterion).list();
    }
}