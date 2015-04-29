package com.IMaylatov.Recommend.Logic.Metric;

import com.IMaylatov.Recommend.webapp.Model.Rate.Ratesable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */


public interface Metric {
    /**
     * ���������� ��������, ������� �������� �������� � ���������� ���������� ����� ����
     */
    double compare(Ratesable person1, Ratesable person2);
}
