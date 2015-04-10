package com.IMaylatov.Recommend.Business.KMeans.FormRate;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * ��������� ������ � ��������
 */
public interface FormRateToCluster {
    /**
     * � �������� ��������� ������. ��� ������ �����, ������������ ������������� � ��������, ���������� ������
     * @param cluster �������, � ������� ����������� ������
     */
    void form(Cluster cluster);
}
