package com.IMaylatov.Recommend.Logic.DAO.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Интерфейс для работы с сущностью Cluster
 */
public interface ClusterDAO extends GenericDAO<Cluster, Long> {
    /**
     * Очистить таблицу кластеров
     * @return Количество затронутых строк - удаленных кластеров
     */
    int deleteAll();
}
