package com.IMaylatov.Recommend.Logic.SVD;

import com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson.ClusteringPersons;
import com.IMaylatov.Recommend.Logic.Metric.Euclid;
import com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate.CalculaterPredicate;
import com.IMaylatov.Recommend.Logic.SVD.GradientDown.GradientDown;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster.RateClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
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
    private ClusterDao clusterDAO;
    @Autowired
    private RateClusterDao rateClusterDAO;
    @Autowired
    private ClusteringPersons clusteringPersons;
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private GradientDown gradientDown;
    @Autowired
    private PersonPredicateDao personPredicateDAO;
    @Autowired
    private CalculaterPredicate calculaterPredicate;

    @Override
    public void calculatePredicate() {
        rateClusterDAO.deleteAll();
        clusterDAO.deleteAll();

        int k = personDAO.list().size() > 100 ? personDAO.list().size() / 100 : 1;

        clusteringPersons.clustering(k, new Euclid());

        List<Cluster> clusters = clusterDAO.list();
        calculaterPredicate.calculate(clusters);

        gradientDown.down(clusters);
    }
}
