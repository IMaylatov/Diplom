package com.IMaylatov.Recommend.webapp.DAO.Model.Person;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface PersonDao extends GenericDao<Person, Long> {
    List<Person> listWithoutLazy();
    Person findWithoutLazy(Long id);
}
