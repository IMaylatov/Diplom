package com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("RatePersonDao")
public class RatePersonDaoImpl extends GenericDaoImpl<RatePerson, PairKey<Person, Song>> implements RatePersonDao {
    @Override
    public List<RatePerson> listWithoutLazy(Criterion criterion) {
        List<RatePerson> rates = currentSession().createCriteria(typeEntity).add(criterion).list();
        for(RatePerson rate : rates) {
            Hibernate.initialize(rate.getSong());
            Hibernate.initialize(rate.getSong().getSongInfo());
        }

        return rates;
    }
}
