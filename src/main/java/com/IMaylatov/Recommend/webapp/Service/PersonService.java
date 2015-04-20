package com.IMaylatov.recommend.webapp.Service;

import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
public interface PersonService {
    /**
     * �������� �������� ������������ ����� ��� ������������
     */
    Song getPrioritySong(Person person);
}
