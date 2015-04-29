package com.IMaylatov.Recommend.Logic.KMeans.BuilderRates;

import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

public interface BuilderRates {
    /**
     * � �������� ��������� ������. ��� ������ �����, ������������ ������������� � ��������, ���������� ������
     */
    void build(Cluster cluster);
}
