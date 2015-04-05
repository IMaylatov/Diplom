package com.IMaylatov.Recommend.Logic.DAO.Model.Rate;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.RatePerson;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Person
 */
@Repository("RatePersonDAO")
public class RatePersonDAOImpl extends GenericDAOImpl<RatePerson, RatePerson.PairKey> implements RatePersonDAO {
}
