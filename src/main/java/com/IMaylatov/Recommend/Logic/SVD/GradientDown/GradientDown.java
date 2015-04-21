package com.IMaylatov.Recommend.Business.SVD.GradientDown;

import com.IMaylatov.Recommend.Logic.Model.Cluster;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface GradientDown {
    /**
     * ���������� ����
     */
    void down(Cluster cluster);
    void down(List<Cluster> clusters);
}