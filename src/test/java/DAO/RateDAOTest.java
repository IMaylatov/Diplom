package DAO;

import com.IMaylatov.Recommend.DAO.Model.Rate.RateDAO;
import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Model.Rate;
import com.IMaylatov.Recommend.Model.Song;
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
public class RateDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private RateDAO rateDAO;
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

        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rate = (Rate) session.get(Rate.class, rate.getId());
        Assert.assertNotNull("Оценка добавлена", rate);
        Assert.assertEquals("Оценка сохранилась верно", 4, rate.getValue());

        // Сохранить оценку с существующим пользователем, но не существующей песней
        song = new Song();
        person = new Person();
        session.save(person);
        rate = new Rate(new Rate.RatePK(person, song), 4);
        try {
            rateDAO.save(rate);
            Assert.assertTrue("Оценка не может быть добавлена", false);
        }catch (Exception ex){}

        song = new Song();
        session.save(song);
        person = new Person();
        rate = new Rate(new Rate.RatePK(person, song), 4);
        try{
            rateDAO.save(rate);
            Assert.assertTrue("Оценка не может быть добавлена", false);
        }catch (Exception ex){}
    }

    @Test
    public void findTest(){
        Person person = new Person();
        Song song = new Song();
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
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
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        session.save(rate);
        rate.setValue(5);
        rateDAO.update(rate);
        rate = (Rate) session.get(Rate.class, rate.getId());
        Assert.assertNotNull("Оценка обновлена", rate);
        Assert.assertEquals("Значение оценки обновлено правильно", 5, rate.getValue());
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        session.save(person);
        Song song = new Song();
        session.save(song);
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        session.save(rate);
        rateDAO.delete(rate);
        rate = (Rate) session.get(Rate.class, rate.getId());
        Assert.assertNull("Оценка удалена", rate);
    }

    @Test
    public void listTest(){
        List<Rate> rateList = new ArrayList<Rate>();
        for (int i = 0; i < 7; i++) {
            Person person = new Person();
            session.save(person);
            Song song = new Song();
            session.save(song);

            Rate rate = new Rate(new Rate.RatePK(person, song), i % 5 + 1);
            rateList.add(rate);
            session.save(rate);
        }
        Assert.assertTrue("Оценки найдены", rateDAO.list().containsAll(rateList));
    }
}
