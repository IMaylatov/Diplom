package com.IMaylatov.Recommend.Logic.KMeans.FormRate;

import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

public interface BuilderRatesable {
    /**
     * � �������� ��������� ������. ��� ������ �����, ������������ ������������� � ��������, ���������� ������
     */
    void form(Cluster cluster);
}
