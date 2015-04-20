package com.IMaylatov.Recommend.Business.SVD.RMSE;

import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public interface RMSE {
    /**
     * Расчитать ошибку для тестовых оценок
     */
    double calculateError(List<RatePerson> ratesTest);
}
