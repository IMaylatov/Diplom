package com.IMaylatov.Recommend.webapp.DAO.Model.Person;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("PersonDao")
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {
    @Override
    public Person loadRates(Person person) {
        Person personFind = (Person) currentSession().get(typeEntity, person.getId());
        Hibernate.initialize(personFind.getRates());
        person.setRates(personFind.getRates());
        return personFind;
    }
}
