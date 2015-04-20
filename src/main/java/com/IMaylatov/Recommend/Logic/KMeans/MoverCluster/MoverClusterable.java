package com.IMaylatov.Recommend.Business.KMeans.MoverCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * ������� ����� ��������, � ����� ���� ������ ������������
 */
public interface MoverClusterable {
    /**
     * ������� ����� ��������. ���� �������� ���������, �� ���������� true
     */
    boolean move(Cluster cluster);
}
