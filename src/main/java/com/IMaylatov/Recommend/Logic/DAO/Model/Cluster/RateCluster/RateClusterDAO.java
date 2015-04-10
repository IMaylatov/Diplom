package com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.RateCluster;
import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * ��������� ��� ������ � �������� RateCluster
 */
public interface RateClusterDAO extends GenericDAO<RateCluster, PairKey<Cluster, Song>> {
    /**
     * �������� ������� ������
     * @return ���������� ���������� - ��������� �����
     * @throws org.hibernate.HibernateException
     */
    int deleteAll();
}
