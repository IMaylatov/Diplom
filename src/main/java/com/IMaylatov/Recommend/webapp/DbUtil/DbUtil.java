package com.IMaylatov.recommend.webapp.dbUtil;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

/**
 * Интерфейс, вуполняющий запросы к БД
 */
public interface DbUtil {
    /**
     * Выполнить sql запрос
     * @param query sql запрос
     * @return Спикок возвращенных объектов
     */
    public int execute(String query);

    /**
     * Вернуть список объктов по результату запроса
     * @param query Запрос на получение данных
     * @return Список объектов после выполнения запроса
     */
    public <V> List<V> retrieve(String query);
}
