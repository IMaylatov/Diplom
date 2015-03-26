package DAO;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.junit.Assert;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

/**
 * Created by Liggoriya on 26.03.2015.
 */

/**
 * Тест для проверки PersonDAO
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void saveTest() {
        int countPersonBeforeAdd = personDAO.list().size();
        Person person = new Person();
        personDAO.save(person);
        List<Person> personList = personDAO.list();
        Assert.assertEquals("Персона добавлена", countPersonBeforeAdd + 1, personList.size());
        Assert.assertTrue("Созданная персона есть в БД", personList.contains(person));
    }

    @Test
    public void findTest(){
        Person person = new Person();
        personDAO.save(person);
        Assert.assertEquals("Персона найдена", person.getId(), personDAO.find(person.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        personDAO.save(person);
        int countPersonBeforeDelete = personDAO.list().size();
        personDAO.delete(person);
        Assert.assertEquals("Персона удалена", countPersonBeforeDelete - 1, personDAO.list().size());
    }
}