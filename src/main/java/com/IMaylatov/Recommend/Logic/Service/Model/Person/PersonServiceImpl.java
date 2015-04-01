package com.IMaylatov.Recommend.Logic.Service.Model.Person;

/**
 * Created by Liggoriya on 24.03.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Service.Generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Класс предоставляющий службы для работы с сущностью Person
 */
@Service("PersonService")
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService{
}
