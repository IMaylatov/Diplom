package com.IMaylatov.Recommend.Logic.Service.Generic;

/**
 * Created by Liggoriya on 24.03.2015.
 */

import java.io.Serializable;
import java.util.List;

/**
 * Общий интерфейс, предоставляющий службы для работы с сущностями
 */
public interface GenericService<T, K extends Serializable> {
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
