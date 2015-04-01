package com.IMaylatov.Recommend.Data;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 31.03.2015.
 */

/**
 * Интрерфейс для загрузки данных
 */
public interface LoaderData {
    /**
     * Загрузить пользователей
     */
    public void loadPerson();

    /**
     * Загрузить песни
     */
    public void loadSong();

    /**
     * Загрузить оценки
     */
    public void loadRate();
}
