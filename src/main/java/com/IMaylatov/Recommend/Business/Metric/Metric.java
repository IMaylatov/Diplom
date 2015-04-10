package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 03.04.2015.
 */


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
    double compare(Pearson person1, Pearson person2) throws IllegalArgumentException;
}
