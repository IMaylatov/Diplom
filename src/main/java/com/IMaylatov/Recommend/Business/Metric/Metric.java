package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Person;

/**
 * ������� ��������� �������������
 */
public interface Metric {
    /**
     * ���������� ������������� � ���������� ���������� ����� ����
     * @param person1 ������ ������������
     * @param person2 ������ ������������
     * @return ���������� ����� ��������������
     */
    double compare(Person person1, Person person2) throws IllegalArgumentException;
}
