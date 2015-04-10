package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson.RatePersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import java.util.ArrayList;
import java.util.List;

/**
 * Тест для проверки RateDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class RatePersonDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private RatePersonDAO rateDAO;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest(){
        // Оценка не может быть добавлена, если песни или пользователя не существует
        // Сохранить оценку с существующими пользователями и песней
        Person person = new Person();
        session.save(person);
        Song song = new Song();
        session.save(song);

        person = (Person) session.get(Person.class, person.getId());
        song = (Song) session.get(Song.class, song.getId());

        RatePerson rate = new RatePerson(new PairKey<>(person, song), 4);
        rateDAO.save(rate);
        rate = (RatePerson) session.get(RatePerson.class, rate.getId());
        Assert.assertNotNull("Оценка добавлена", rate);
        Assert.assertEquals("Оценка сохранилась верно", 4, rate.getValue());

        // Сохранить оценку с существующим пользователем, но несуществующей песней
        song = new Song();
        person = new Person();
        session.save(person);
        rate = new RatePerson(new PairKey<>(person, song), 4);
        try {
            rateDAO.save(rate);
            Assert.assertTrue("Оценка не может быть добавлена", false);
        }catch (Exception ex){}

        // Сохранить оценку с несуществующим пользователем, но с существующей песней
        song = new Song();
        session.save(song);
        person = new Person();
        rate = new RatePerson(new PairKey<>(person, song), 4);
        try{
            rateDAO.save(rate);
            Assert.assertTrue("Оценка не может быть добавлена", false);
        }catch (Exception ex){}
    }

    @Test
    public void findTest(){
        Person person = new Person();
        Song song = new Song();
        RatePerson rate = new RatePerson(new PairKey<>(person, song), 4);
        session.save(rate);
        rate = rateDAO.find(rate.getId());
        Assert.assertNotNull("Оценка найдена", rate);
        Assert.assertEquals("Оценка найдена верно", 4, rate.getValue());
    }

    @Test
    public void updateTest(){
        Person person = new Person();
        session.save(person);
        Song song = new Song();
        session.save(song);
        RatePerson rate = new RatePerson(new PairKey<>(person, song), 4);
        session.save(rate);
        rate.setValue(5);
        rateDAO.update(rate);
        rate = (RatePerson) session.get(RatePerson.class, rate.getId());
        Assert.assertNotNull("Оценка обновлена", rate);
        Assert.assertEquals("Значение оценки обновлено правильно", 5, rate.getValue());
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        session.save(person);
        Song song = new Song();
        session.save(song);
        RatePerson rate = new RatePerson(new PairKey<>(person, song), 4);
        session.save(rate);
        rateDAO.delete(rate);
        rate = (RatePerson) session.get(RatePerson.class, rate.getId());
        Assert.assertNull("Оценка удалена", rate);
    }

    @Test
    public void listTest(){
        List<RatePerson> rateList = new ArrayList<RatePerson>();
        for (int i = 0; i < 7; i++) {
            Person person = new Person();
            session.save(person);
            Song song = new Song();
            session.save(song);

            RatePerson rate = new RatePerson(new PairKey<>(person, song), i % 5 + 1);
            rateList.add(rate);
            session.save(rate);
        }
        Assert.assertTrue("Оценки найдены", rateDAO.list().containsAll(rateList));
    }
}
