package com.IMaylatov.recommend.webapp.dao.model.person.ratePerson;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.RatePerson;
import org.springframework.stereotype.Repository;

@Repository("RatePersonDao")
public class RatePersonDaoImpl extends GenericDaoImpl<RatePerson, PairKey<Person, Song>> implements RatePersonDao {
}
