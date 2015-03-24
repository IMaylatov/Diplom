package com.IMaylatov.Recommend.DAO.Entity.Person;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Entity.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Person
 */
@Repository("PersonDAO")
public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {
}
