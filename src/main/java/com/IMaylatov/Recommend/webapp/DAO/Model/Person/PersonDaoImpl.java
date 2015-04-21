package com.IMaylatov.Recommend.webapp.DAO.Model.Person;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("PersonDAO")
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {
}
