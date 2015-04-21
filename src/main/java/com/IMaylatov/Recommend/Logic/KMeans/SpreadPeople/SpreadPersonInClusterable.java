package com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;

import java.util.List;

/**
 * ����������� ������������� �� ���������
 */
public interface SpreadPersonInClusterable {
    /**
     * ���������� ������������ ������������� �� ���������
     * @return ������ ���������, ���������� �������������� �������������
     */
    List<Cluster> simpleSpread(List<Person> persons, int k, BuilderRatesable formingRateInCluster);

    /**
     * ������������ ������������� �� ��������� ��������� �� �������� �������
     * @return ������ �������������� �������������
     */
   //List<Person> distanceSpread(List<Cluster> clusters, List<Person> persons, Metric metric);
}
