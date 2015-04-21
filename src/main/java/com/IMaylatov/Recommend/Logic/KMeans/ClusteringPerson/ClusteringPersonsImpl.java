package com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.BuilderRatesable;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverClusterable;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInClusterable;
import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015.
 */


@Repository("ClusteringPersons")
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public class ClusteringPersonsImpl implements ClusteringPersons{
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private RateClusterDAO rateClusterDAO;
    @Autowired
    private PersonDAO personDAO;

    private final int MAX_ITERATION = 10;

    private Metric metric;
    private SpreadPersonInClusterable spread;
    private BuilderRatesable formingRate;
    private MoverClusterable mover;

    private ClusteringPersonsImpl(){}

    public ClusteringPersonsImpl setMetric(Metric metric){
        this.metric = metric;
        return this;
    }

    public ClusteringPersonsImpl setSpread(SpreadPersonInClusterable spread){
        this.spread = spread;
        return this;
    }

    public ClusteringPersonsImpl setFormingRate(BuilderRatesable formingRate){
        this.formingRate = formingRate;
        return this;
    }

    public ClusteringPersonsImpl setMover(MoverCluster mover){
        this.mover = mover;
        return this;
    }

    /**
     * ����� ������������ ������������� �� ���������, �������� ������� ���� �������� � ������� �� ������.
     * ����������� ������������� �� ��������� � ������� �� �����������. �� ������� ���� �������������
     * ���������� ������ ��������. ������ �������� ����� ������� ����������. ��� ��� ������� ����� �����,
     * �� ������������ ����� ����� � ���� ���������� � �������������� � ������ ���������� ��������.
     * ������ ������� ����� � �������������, ������� ��� �����������, � ������ ����� ������������� � ����� �� ����.
     * ���� �������� ���������, ����� ���������������� ������������� � ������� �������� � ����� ����
     */
    @Override
    public void spread(int k) {
        if (metric == null || spread == null || formingRate == null || mover == null)
            throw new IllegalArgumentException("Null field{"+
                                                " Metric=" + metric +
                                                " Spread=" + spread +
                                                " FormingRate=" + formingRate +
                                                " Mover=" + mover);

        List<Person> persons = personDAO.list();
        List<Cluster>  clusters = spread.simpleSpread(persons, k, formingRate);

        boolean isMove = false;
        int i = 0;
        do {
            spread.distanceSpread(clusters, persons, metric);
            for (Cluster cluster : clusters)
                if (mover.move(cluster))
                    isMove = true;
        }while ((isMove) && (i++ < MAX_ITERATION));

        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        for(Person person : persons)
            personDAO.save(person);
    }
}
