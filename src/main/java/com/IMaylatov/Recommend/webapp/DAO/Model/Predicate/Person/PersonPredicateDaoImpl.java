package com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Predicate.PersonPredicate;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("PersonPredicateDao")
public class PersonPredicateDaoImpl extends GenericDaoImpl<PersonPredicate, Long> implements PersonPredicateDao {
}
