package com.IMaylatov.Recommend.Logic.SVD.RMSE;

import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRate;
import com.IMaylatov.Recommend.Logic.SVD.DealerRate.DealerRateImpl;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public class RMSEImpl implements RMSE {
    @Override
    public double calculateError(List<RatePerson> ratesTest) {
        float error = 0;
        DealerRate dealerRate = new DealerRateImpl();
        for(RatePerson rate : ratesTest){
            int expectedRate = dealerRate.getRateInt(rate.getPerson(), rate.getSong());
            error += Math.pow(rate.getValue() - expectedRate, 2);
        }
        return Math.sqrt(error / ratesTest.size());
    }
}
