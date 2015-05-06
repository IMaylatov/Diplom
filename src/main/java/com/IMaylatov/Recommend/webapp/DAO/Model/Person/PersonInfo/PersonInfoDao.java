package com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonInfo;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Person.PersonInfo;
import org.hibernate.criterion.Criterion;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
public interface PersonInfoDao extends GenericDao<PersonInfo, Long> {
    List<PersonInfo> listWithoutLazy(Criterion criterion);
}
