package com.IMaylatov.Recommend.webapp.Service;


import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
public interface PersonService {
    /**
     * Получить наиболее приоритетную песню для пользователя
     */
    Song getPrioritySong(Person person);
}
