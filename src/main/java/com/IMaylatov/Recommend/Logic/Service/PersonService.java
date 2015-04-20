package com.IMaylatov.Recommend.Logic.Service;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;

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
