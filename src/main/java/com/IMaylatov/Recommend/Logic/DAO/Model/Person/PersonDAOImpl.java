package com.IMaylatov.Recommend.Logic.DAO.Model.Person;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Person;
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
