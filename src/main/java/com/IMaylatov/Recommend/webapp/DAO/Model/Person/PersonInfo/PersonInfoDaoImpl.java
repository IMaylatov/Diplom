package com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonInfo;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Repository("PersonInfoDao")
public class PersonInfoDaoImpl extends GenericDaoImpl<PersonInfo, Long> implements PersonInfoDao  {
}
