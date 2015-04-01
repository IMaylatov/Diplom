package com.IMaylatov.Recommend.Logic.DAO.Generic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Общий класс для работы с сущностями
 * @param <T>
 */
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public abstract class GenericDAOImpl<T, K extends Serializable> implements GenericDAO<T, K> {
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
    public GenericDAOImpl() {
        typeEntity = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    //endregion

    //region public method
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        currentSession().save(entity);
        currentSession().flush();
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
    public void shutdown(){
        currentSession().createSQLQuery("SHUTDOWN").executeUpdate();
    }
    //endregion
}
