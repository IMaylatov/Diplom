package com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;


/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Интерфейс для работы с сущностью RatePerson
 */
public interface RatePersonDAO extends GenericDAO<RatePerson, PairKey<Person, Song>> {
}
