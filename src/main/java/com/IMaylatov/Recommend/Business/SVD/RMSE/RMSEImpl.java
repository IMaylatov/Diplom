package com.IMaylatov.Recommend.Business.SVD.RMSE;

import com.IMaylatov.Recommend.Business.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Business.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson.RatePersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public class RMSEImpl implements RMSE{
    @Override
    public double calculateError(List<RatePerson> ratesTest) {
        float error = 0;
        DealerRate dealerRate = new DealerRateImpl();
        for(RatePerson rate : ratesTest){
            int expectedRate = dealerRate.getRate(rate.getPerson(), rate.getSong());
            error += Math.pow(rate.getValue() - expectedRate, 2);
        }
        return Math.sqrt(error / ratesTest.size());
    }
}
