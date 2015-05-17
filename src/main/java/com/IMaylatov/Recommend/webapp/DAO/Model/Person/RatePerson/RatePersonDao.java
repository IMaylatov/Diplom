package com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface RatePersonDao extends GenericDao<RatePerson, PairKey<Person, Song>> {
    List<RatePerson> listWithoutLazy(Criterion criterion);
}