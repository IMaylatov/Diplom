package com.IMaylatov.recommend.webapp.dao.model.person;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Person;
import org.springframework.stereotype.Repository;

@Repository("PersonDAO")
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {
}
