package com.IMaylatov.Recommend.Logic.SVD.RMSE;

import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface RMSE {
    /**
     * Расчитать ошибку
     */
    double calculateError(List<RatePerson> ratesTest);
}
