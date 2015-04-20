package com.IMaylatov.recommend.webapp.dao.Model.Person;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Person;
import org.springframework.stereotype.Repository;

/**
 * Класс для работы с сущность Person
 */
@Repository("PersonDAO")
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {
}
