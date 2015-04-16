package com.IMaylatov.Recommend.Business.SVD.CalculaterPredicate;

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public interface CalculaterPredicateable {
    /**
     * ����������� ��������� ��� ������������� � ����� � ��������
     */
    void calculate(Cluster cluster);
}
