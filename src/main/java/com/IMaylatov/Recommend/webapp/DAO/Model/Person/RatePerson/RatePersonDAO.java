package com.IMaylatov.recommend.webapp.dao.Model.Person.RatePerson;

import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;


/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Интерфейс для работы с сущностью RatePerson
 */
public interface RatePersonDAO extends GenericDAO<RatePerson, PairKey<Person, Song>> {
}
