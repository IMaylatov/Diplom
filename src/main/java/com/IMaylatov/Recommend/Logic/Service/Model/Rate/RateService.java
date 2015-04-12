package com.IMaylatov.Recommend.Logic.Service.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;
import com.IMaylatov.Recommend.Logic.Service.Generic.GenericService;

/**
 * Created by Liggoriya on 24.03.2015.
 */

/**
 * Интерфейс предоставляющий службы для работы с сущностью RatePerson
 */
public interface RateService  extends GenericService<RatePerson, PairKey<Person, Song>> {
}
