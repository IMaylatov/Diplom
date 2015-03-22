package com.IMaylatov.Recommend.DAO.Entity.Rate;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Entity.Rate;
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
