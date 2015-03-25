package com.IMaylatov.Recommend.DAO.Model.Person;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Model.Person;
import org.springframework.stereotype.Repository;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Класс для работы с сущность Person
 */
@Repository("PersonDAO")
public class PersonDAOImpl extends GenericDAOImpl<Person, Long> implements PersonDAO {
}
