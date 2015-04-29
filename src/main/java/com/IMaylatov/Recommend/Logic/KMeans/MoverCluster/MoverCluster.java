package com.IMaylatov.Recommend.Logic.KMeans.MoverCluster;

import com.IMaylatov.Recommend.webapp.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */

public interface MoverCluster {
    /**
     * Двигает центр кластера. Если движение произошло, то возвращает true
     */
    boolean move(Cluster cluster);
}
