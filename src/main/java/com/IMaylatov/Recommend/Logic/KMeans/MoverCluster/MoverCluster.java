package com.IMaylatov.Recommend.Logic.KMeans.MoverCluster;

import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */

public interface MoverCluster {
    /**
     * ������� ����� ��������. ���� �������� ���������, �� ���������� true
     */
    boolean move(Cluster cluster);
}
