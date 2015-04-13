package com.IMaylatov.Recommend.Business.KMeans.MoverCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Двигает центр кластера, в центр масс оценок пользователя
 */
public interface MoverClusterable {
    /**
     * Двигает центр кластера. Если движение произошло, то возвращает true
     */
    boolean move(Cluster cluster);
}
