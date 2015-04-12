package com.IMaylatov.Recommend.Logic.Service.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;
import com.IMaylatov.Recommend.Logic.Service.Generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Liggoriya on 24.03.2015.
 */

/**
 * Класс предоставляющий службы для работы с сущностью RatePerson
 */
@Service("RateService")
public class RateServiceImpl extends GenericServiceImpl<RatePerson, PairKey<Person, Song>> implements RateService{
}
