package com.IMaylatov.recommend.webapp.dao.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDao;
import com.IMaylatov.recommend.webapp.model.Cluster;

/**
 * ��������� ��� ������ � ��������� Cluster
 */
public interface ClusterDao extends GenericDao<Cluster, Long> {
    /**
     * �������� ������� ���������
     * @return ���������� ���������� ����� - ��������� ���������
     */
    int deleteAll();
}
