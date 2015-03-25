package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.DbContext;
import com.IMaylatov.Recommend.Model.Person;


/**
 * Created by Liggoriya on 21.03.2015.
 */
public class Main {
    public static void main(String[] args) {
        PersonDAO personDAO = DbContext.INSTANCE.getDAO("PersonDAO");
        Person person = personDAO.find(1L);

        Person person1 = new Person();
        personDAO.save(person1);

        System.out.println();
    }
}
