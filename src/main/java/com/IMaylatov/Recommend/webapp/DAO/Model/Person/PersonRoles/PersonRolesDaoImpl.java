package com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonRoles;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonRoles;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 30.04.2015
 */
@Repository("PersonRolesDao")
public class PersonRolesDaoImpl extends GenericDaoImpl<PersonRoles, PersonRoles.PairKey> implements PersonRolesDao {
}
