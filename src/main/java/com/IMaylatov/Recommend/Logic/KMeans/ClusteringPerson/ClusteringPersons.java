package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.BuilderRatesable;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInClusterable;
import com.IMaylatov.Recommend.Business.Metric.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */

public interface ClusteringPersons {
    /**
     * ������� k ��������� � ������������ �� ��� ����� � ������������ �������
     */
    void spread(int k);

    /**
     * ������������� �������
     */
    ClusteringPersons setMetric(Metric metric);

    /**
     * ������������� �����, ������� ������������ ������������� �� ���������
     */
    ClusteringPersons setSpread(SpreadPersonInClusterable spread);

    /**
     * ������������� �����, ������� ��������� ���������� ������ - ��������
     */
    ClusteringPersons setFormingRate(BuilderRatesable formingRate);

    /**
     * ������������� �����, ������� ������� �������� � ����� ���� �������������, ������������� ��������
     */
    ClusteringPersons setMover(MoverCluster mover);
}
