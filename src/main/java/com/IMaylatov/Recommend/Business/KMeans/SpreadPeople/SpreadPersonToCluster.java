package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.List;

/**
 * ����������� ������������� �� ���������
 */
public interface SpreadPersonToCluster {
    /**
     * ���������� ������������ ������������� �� ���������
     * @param persons ������������
     * @param k ���������� ���������
     * @return ������ ���������, ���������� �������������� �������������
     */
    List<Cluster> simpleSpread(List<Person> persons, int k);
}
