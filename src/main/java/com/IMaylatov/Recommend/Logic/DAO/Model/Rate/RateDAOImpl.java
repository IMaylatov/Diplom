package com.IMaylatov.Recommend.Logic.DAO.Model.Rate;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Rate;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Person
 */
@Repository("RateDAO")
public class RateDAOImpl extends GenericDAOImpl<Rate, Rate.RatePK> implements RateDAO {
}
