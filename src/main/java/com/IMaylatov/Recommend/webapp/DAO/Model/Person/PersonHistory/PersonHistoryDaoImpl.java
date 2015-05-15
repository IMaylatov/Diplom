package com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonHistory;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonHistory;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Repository("PersonHistoryDao")
public class PersonHistoryDaoImpl extends GenericDaoImpl<PersonHistory, Long> implements PersonHistoryDao {
}
