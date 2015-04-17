package com.IMaylatov.Recommend.Business.SVD.GradientDown;

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface GradientDown {
    /**
     * Спуститься вниз
     */
    void down(Cluster cluster);
}
