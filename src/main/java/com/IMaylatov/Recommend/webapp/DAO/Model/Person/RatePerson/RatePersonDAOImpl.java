package com.IMaylatov.recommend.webapp.dao.Model.Person.RatePerson;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAOImpl;
import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Rate.PairKey.PairKey;
import com.IMaylatov.recommend.webapp.model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Person
 */
@Repository("RatePersonDAO")
public class RatePersonDAOImpl extends GenericDAOImpl<RatePerson, PairKey<Person, Song>> implements RatePersonDAO {
}
