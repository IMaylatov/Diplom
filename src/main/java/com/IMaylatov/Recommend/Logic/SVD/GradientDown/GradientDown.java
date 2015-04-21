package com.IMaylatov.Recommend.Logic.SVD.GradientDown;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface GradientDown {
    /**
     * Спуститься вниз, стохастический спуск
     */
    void down(Cluster cluster);
    void down(List<Cluster> clusters);
}
