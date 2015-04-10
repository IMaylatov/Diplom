package com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;
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
