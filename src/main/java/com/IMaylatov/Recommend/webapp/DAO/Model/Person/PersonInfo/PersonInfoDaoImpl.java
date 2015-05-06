package com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonInfo;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Repository("PersonInfoDao")
public class PersonInfoDaoImpl extends GenericDaoImpl<PersonInfo, Long> implements PersonInfoDao  {
    @Override
    public List<PersonInfo> listWithoutLazy(Criterion criterion) {
        List<PersonInfo> persons = currentSession().createCriteria(typeEntity).add(criterion).list();
        for(PersonInfo person : persons){
            Hibernate.initialize(person.getPerson());
        }
        return persons;
    }
}
