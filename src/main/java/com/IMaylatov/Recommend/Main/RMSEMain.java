package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.Logic.LoaderData.LoaderData;
import com.IMaylatov.Recommend.Logic.SVD.RMSE.RMSE;
import com.IMaylatov.Recommend.Logic.SVD.RMSE.RMSEImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 22.04.2015
 */
public class RMSEMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        LoaderData loaderData = (LoaderData) context.getBean("LoaderData");
        List<RatePerson> ratesTest = loaderData.loadTestRate("data/testRate.dat");
        RMSE rmse = new RMSEImpl();
        double error = rmse.calculateError(ratesTest);
        System.out.println("RMSE = " + error);
    }
}
