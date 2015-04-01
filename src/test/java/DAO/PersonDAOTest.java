package DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.junit.Assert;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Тест для проверки PersonDAO
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest() {
        Person person = new Person();
        personDAO.save(person);
        Person findPerson = (Person) session.get(Person.class, person.getId());
        Assert.assertNotNull("Персона добавлена", findPerson);
        Assert.assertTrue("Созданная персона есть в БД", person.equals(findPerson));
    }

    @Test
    public void findTest(){
        Person person = new Person();
        session.save(person);
        Assert.assertEquals("Персона найдена", person.getId(), personDAO.find(person.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        session.save(person);
        personDAO.delete(person);
        person = (Person) session.get(Person.class, person.getId());
        Assert.assertNull("Персона удалена", person);
    }

    @Test
    public void listTest(){
        List<Person> personList = new ArrayList<Person>();
        for (int i = 0; i < 7; i++) {
            Person person = new Person();
            personList.add(person);
            session.save(person);
        }
        Assert.assertTrue("Персоны найдены", personDAO.list().containsAll(personList));
    }
}