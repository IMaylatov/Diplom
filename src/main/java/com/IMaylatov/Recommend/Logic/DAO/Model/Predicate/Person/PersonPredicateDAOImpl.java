package com.IMaylatov.Recommend.Logic.DAO.Model.Predicate.Person;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Predicate.PersonPredicate;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("PersonPredicateDAO")
public class PersonPredicateDAOImpl extends GenericDAOImpl<PersonPredicate, Long> implements PersonPredicateDAO{
}
