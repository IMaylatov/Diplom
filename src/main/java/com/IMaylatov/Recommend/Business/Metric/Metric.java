package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */


import com.IMaylatov.Recommend.Logic.Model.Rate.HasRates;

public interface Metric {
    /**
     * ���������� ��������, ������� �������� �������� � ���������� ���������� ����� ����
     */
    double compare(HasRates person1, HasRates person2) throws IllegalArgumentException;
}
