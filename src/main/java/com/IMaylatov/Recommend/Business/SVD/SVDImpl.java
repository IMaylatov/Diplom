package com.IMaylatov.Recommend.Business.SVD;

import com.IMaylatov.Recommend.Business.KMeans.ClusteringPersons;
import com.IMaylatov.Recommend.Business.KMeans.FormRate.BuilderRates;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInCluster;
import com.IMaylatov.Recommend.Business.Metric.Pearson;
import com.IMaylatov.Recommend.Business.SVD.CalculaterPredicate.CalculaterPredicate;
import com.IMaylatov.Recommend.Business.SVD.CalculaterPredicate.CalculaterPredicateImpl;
import com.IMaylatov.Recommend.Business.SVD.GradientDown.GradientDown;
import com.IMaylatov.Recommend.Business.SVD.GradientDown.GradientDownImpl;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
@Repository("SVD")
public class SVDImpl implements SVD {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private RateClusterDAO rateClusterDAO;
    @Autowired
    private ClusteringPersons clusteringPersons;
    @Autowired
    private PersonDAO personDAO;

    @Override
    public void calculatePredicate() {
        // Удаляем кластера и кластерные оценки
        rateClusterDAO.deleteAll();
        clusterDAO.deleteAll();

        int k = personDAO.list().size() / 100;

        // Формируем кластера и размещаем в них пользователей
        clusteringPersons.setFormingRate(new BuilderRates())
                .setMetric(new Pearson())
                .setMover(new MoverCluster())
                .setSpread(new SpreadPersonInCluster());
        clusteringPersons.spread(k);

        List<Cluster> clusters = clusterDAO.list();
        CalculaterPredicate calculaterPredicate = new CalculaterPredicateImpl();
        calculaterPredicate.calculate(clusters);

        GradientDown gradientDown = new GradientDownImpl();
        gradientDown.down(clusters);
    }
}
