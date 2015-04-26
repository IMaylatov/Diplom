package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.Logic.SVD.SVD;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.RateCluster.RateClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Song.SongPredicateDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 22.04.2015
 */
public class DeleteAllPredicateMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");

        SongPredicateDao songPredicateDao = (SongPredicateDao) context.getBean("SongPredicateDao");
        songPredicateDao.deleteAll();

        PersonPredicateDao personPredicateDao = (PersonPredicateDao) context.getBean("PersonPredicateDao");
        personPredicateDao.deleteAll();

        RateClusterDao rateClusterDao = (RateClusterDao) context.getBean("RateClusterDao");
        rateClusterDao.deleteAll();

        ClusterDao clusterDao = (ClusterDao) context.getBean("ClusterDao");
        clusterDao.deleteAll();
    }

}
