package com.IMaylatov.Recommend.Logic.Service.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Rate;
import com.IMaylatov.Recommend.Logic.Service.Generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Liggoriya on 24.03.2015.
 */

/**
 * Класс предоставляющий службы для работы с сущностью Rate
 */
@Service("RateService")
public class RateServiceImpl extends GenericServiceImpl<Rate, Rate.RatePK> implements RateService{
}
