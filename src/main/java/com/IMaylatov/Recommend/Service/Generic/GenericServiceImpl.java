package com.IMaylatov.Recommend.Service.Generic;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liggoriya on 24.03.2015.
 */

/**
 * Класс предоставляющий службы для работы с сущностями
 * @param <T>
 * @param <K>
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public abstract class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K>  {
    //region field
    @Autowired
    private GenericDAO<T, K> genericDAO;
    //endregion

    //region constructor
    public GenericServiceImpl(GenericDAO<T, K> genericDao) {
        this.genericDAO = genericDao;
    }

    public GenericServiceImpl() {
    }
    //endregion

    //region public method
    @Override
    public void save(T entity) {
        genericDAO.save(entity);
    }

    @Override
    public void update(T entity) {
        genericDAO.update(entity);
    }

    @Override
    public void delete(T entity) {
        genericDAO.delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T find(K id) {
        return genericDAO.find(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> list() {
        return genericDAO.list();
    }
    //endregion
}
