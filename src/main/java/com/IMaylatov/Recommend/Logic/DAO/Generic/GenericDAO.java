package com.IMaylatov.Recommend.Logic.DAO.Generic;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Общий интерфейс для работы с сущностями
 * @param <T> сущность
 */
public interface GenericDAO<T, K extends Serializable> {
    /**
     * Сохранить сущность
     * @param entity сохраняемая сущность
     */
    public void save(T entity);

    /**
     * Обновить сущность
     * @param entity обновляемая сущность
     */
    public void update(T entity);

    /**
     * Удалить сущность
     * @param entity удаляемая сущность
     */
    public void delete(T entity);

    /**
     * Найти сущность по id
     * @param id id сущности
     * @return найденная сущность
     */
    public T find(K id);

    /**
     * Список сущностей
     * @return возвращаемый список
     */
    public List<T> list();
}
