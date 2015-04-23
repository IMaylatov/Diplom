package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.Logic.LoaderData.LoaderData;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.Logic.SVD.RMSE.RMSE;
import com.IMaylatov.Recommend.Logic.SVD.RMSE.RMSEImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 22.04.2015
 */
public class RMSEMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        PersonDao personDao = (PersonDao) context.getBean("PersonDao");
        SongDao songDao = (SongDao) context.getBean("SongDao");
        ClusterDao clusterDao = (ClusterDao) context.getBean("ClusterDao");
        DbUtil dbUtil = (DbUtil) context.getBean("DbUtil");

        double error = 0;
        int countRate = 0;
        DealerRate dealerRate = new DealerRateImpl();
        try(InputStream stream = java.lang.ClassLoader.getSystemResourceAsStream("data/testRate.dat");
            Scanner scanner = new Scanner(stream)){
            while (scanner.hasNext()) {
                String[] rateInfo = scanner.nextLine().split(" ");
                Long personId = Long.parseLong(rateInfo[0]);
                Long songId = Long.parseLong(rateInfo[1]);
                Integer rate = Integer.parseInt(rateInfo[2]);
                countRate++;
            }
        }catch (IOException e){
        }
        error /= countRate;
        System.out.println("RMSE = " + error);
    }
}
