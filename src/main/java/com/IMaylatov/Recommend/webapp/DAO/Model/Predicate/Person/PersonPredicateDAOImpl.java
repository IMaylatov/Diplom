package com.IMaylatov.recommend.webapp.dao.Model.Predicate.Person;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAOImpl;
import com.IMaylatov.recommend.webapp.model.predicate.PersonPredicate;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("PersonPredicateDAO")
public class PersonPredicateDAOImpl extends GenericDAOImpl<PersonPredicate, Long> implements PersonPredicateDAO{
}
