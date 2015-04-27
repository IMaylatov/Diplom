package com.IMaylatov.Recommend.webapp.DAO.Model.Person;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("PersonDao")
public class PersonDaoImpl extends GenericDaoImpl<Person, Long> implements PersonDao {
    @Override
    @SuppressWarnings("unchecked")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Person> listWithoutLazy() {
        List<Person> persons = currentSession().createCriteria(typeEntity).list();
        for(Person person : persons) {
            Hibernate.initialize(person.getRates());
            for(Map.Entry<Song, Integer> rate : person.getRates().entrySet())
                Hibernate.initialize(rate.getKey().getPredicates());
        }
        return persons;
    }

    @Override
    public Person findWithoutLazy(Long id) {
        Person person = (Person) currentSession().get(typeEntity, id);
        Hibernate.initialize(person.getRates());
        return person;
    }
}
