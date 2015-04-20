package com.IMaylatov.Recommend.Business.SVD.DealerRate;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface DealerRate {
    /**
     * ������ �������������� ������ ������������ ��� �����
     */
    int getRate(Person person, Song song);
}
