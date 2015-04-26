package com.IMaylatov.Recommend.Logic.SVD.DealerRate;

import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface DealerRate {
    /**
     * Выдает предполагаемую оценку пользователя для песни
     */
    int getRateInt(Person person, Song song);
    float getRateFloat(Person person, Song song);
}
