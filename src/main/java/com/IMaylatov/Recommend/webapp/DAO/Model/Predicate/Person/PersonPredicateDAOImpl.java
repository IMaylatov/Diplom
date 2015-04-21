package com.IMaylatov.recommend.webapp.dao.model.predicate.person;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.predicate.PersonPredicate;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("PersonPredicateDao")
public class PersonPredicateDaoImpl extends GenericDaoImpl<PersonPredicate, Long> implements PersonPredicateDao {
}
