package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
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

    /**
     * ������������ ������������� �� ��������� � ������ �� ���������� �� �������� �� �������� �������
     * @param clusters �������� �� ������� ���������� ������������
     * @param persons ������������, ������� ���������� ������������
     * @param metric �������, �� ������� ���������� �������������
     * @return List<Person> ������ �������������� �������������
     */
    List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric);
}
