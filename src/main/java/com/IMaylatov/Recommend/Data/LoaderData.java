package com.IMaylatov.Recommend.Data;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 31.03.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;

import java.util.List;
import java.util.Map;

/**
 * Интрерфейс для загрузки данных
 */
public interface LoaderData {
    /**
     * Загрузить информацию из пакета для перечисленных файлов
     * @param files Имена файлов
     */
    void loadAll(Map<String, String> files);

    /**
     * Загрузить пользователей
     */
    void loadPerson(String personFile);

    /**
     * Загрузить песни
     */
    void loadSong(String songFile);

    /**
     * Загрузить оценки
     */
    void loadRate(String rateFile);

    /**
     * Вернуть список тестовых оценок
     */
    List<RatePerson> loadTestRate(String testRateFile);
}
