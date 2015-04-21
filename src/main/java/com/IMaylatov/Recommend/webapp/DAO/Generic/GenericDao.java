package com.IMaylatov.Recommend.webapp.DAO.Generic;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
    /**
     * Сохранить сущность
     * @param entity сохраняемая сущность
     */
    void save(T entity);

    /**
     * Обновить сущность
     * @param entity обновляемая сущность
     */
    void update(T entity);

    /**
     * Удалить сущность
     * @param entity удаляемая сущность
     */
    void delete(T entity);

    /**
     * Найти сущность по id
     * @param id id сущности
     * @return найденная сущность
     */
    T find(K id);

    /**
     * Принудительной выполнение накопленных запросов
     */
    void flush();

    /**
     * Список сущностей
     */
    List<T> list();
}