package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Predicate.Person.PersonPredicateDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Predicate.PersonPredicate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonPredicateTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonPredicateDAO personPredicateDAO;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest(){
        Person person = new Person();
        session.save(person);

        PersonPredicate personPredicate = new PersonPredicate(person);
        personPredicate.setValue(3.5f);
        personPredicateDAO.save(personPredicate);

        personPredicate = (PersonPredicate) session.get(PersonPredicate.class, personPredicate.getId());
        Assert.assertNotNull(personPredicate);
        Assert.assertEquals(3.5f, personPredicate.getValue(), 0.001f);
    }

    @Test
    public void findTest(){
        Person person = new Person();
        session.save(person);

        PersonPredicate personPredicate = new PersonPredicate(person);
        personPredicate.setValue(3.5f);
        session.save(personPredicate);

        personPredicate = personPredicateDAO.find(personPredicate.getId());
        Assert.assertNotNull(personPredicate);
        Assert.assertEquals(3.5f, personPredicate.getValue(), 0.001f);
    }

    @Test
    public void updateTest(){
        Person person = new Person();
        session.save(person);

        PersonPredicate personPredicate = new PersonPredicate(person);
        personPredicate.setValue(3.5f);
        session.save(personPredicate);

        personPredicate.setValue(4f);
        personPredicateDAO.update(personPredicate);
        personPredicate = (PersonPredicate) session.get(PersonPredicate.class, personPredicate.getId());
        Assert.assertEquals(4f, personPredicate.getValue(), 0.001f);
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        session.save(person);

        PersonPredicate personPredicate = new PersonPredicate(person);
        personPredicate.setValue(3.5f);
        session.save(personPredicate);

        personPredicateDAO.delete(personPredicate);
        personPredicate = (PersonPredicate) session.get(PersonPredicate.class, personPredicate.getId());
        Assert.assertNull(personPredicate);
    }

    @Test
    public void listTest() {
        List<PersonPredicate> personPredicates = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            Person person = new Person();
            session.save(person);

            PersonPredicate personPredicate = new PersonPredicate(person);
            personPredicate.setValue(i);
            session.save(personPredicate);
            personPredicates.add(personPredicate);
        }
        List<PersonPredicate> personPredicatesFind = personPredicateDAO.list();

        Assert.assertEquals(personPredicates.size(), personPredicatesFind.size());
        for (int i = 0; i < personPredicates.size(); i++)
            Assert.assertEquals(personPredicates.get(i).getId(), personPredicatesFind.get(i).getId());
    }
}
