package com.IMaylatov.recommend.webapp.dao.model.person.ratePerson;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDao;
import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.RatePerson;

public interface RatePersonDao extends GenericDao<RatePerson, PairKey<Person, Song>> {
}
