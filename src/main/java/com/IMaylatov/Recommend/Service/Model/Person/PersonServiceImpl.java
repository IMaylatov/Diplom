package com.IMaylatov.Recommend.Service.Model.Person;

/**
 * Created by Liggoriya on 24.03.2015.
 */

import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Service.Generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Класс предоставляющий службы для работы с сущностью Person
 */
@Service("PersonService")
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService{
}
