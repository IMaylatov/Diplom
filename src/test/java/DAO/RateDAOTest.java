package DAO;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.DAO.Model.Rate.RateDAO;
import com.IMaylatov.Recommend.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Model.Rate;
import com.IMaylatov.Recommend.Model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Тест для проверки RateDAO
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class RateDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private RateDAO rateDAO;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;

    @Test
    public void saveTest(){
        // Сохранить оценку с существующими пользователями и песней
        Person person = new Person();
        personDAO.save(person);
        Song song = new Song();
        songDAO.save(song);

        person = personDAO.find(person.getId());
        song = songDAO.find(song.getId());

        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rate = rateDAO.find(new Rate.RatePK(person, song));
        Assert.assertNotNull("Оценка добавлена", rate);
        Assert.assertEquals("Оценка сохранилась верно", 4, rate.getValue());

        // Сохранить оценку с существующим пользователем, но не существующей оценкой
        song = new Song();
        person = new Person();
        personDAO.save(person);
        person = personDAO.find(person.getId());
        rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rate = rateDAO.find(new Rate.RatePK(person, song));
        Assert.assertNotNull("Оценка добавлена", rate);
        Assert.assertEquals("Оценка сохранена верно", 4, rate.getValue());
    }

    @Test
    public void findTest(){
        Person person = new Person();
        Song song = new Song();
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rate = rateDAO.find(rate.getId());
        Assert.assertNotNull("Оценка найдена", rate);
        Assert.assertEquals("Оценка найдена верно", 4, rate.getValue());
    }

    @Test
    public void updateTest(){
        Person person = new Person();
        personDAO.save(person);
        Song song = new Song();
        songDAO.save(song);
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rate.setValue(5);
        rateDAO.update(rate);

        rate = rateDAO.find(rate.getId());

        Assert.assertNotNull("Оценка обновлена", rate);
        Assert.assertEquals("Значение оценки обновлено правильно", 5, rate.getValue());
    }

    @Test
    public void deleteTest(){
        Person person = new Person();
        personDAO.save(person);
        Song song = new Song();
        songDAO.save(song);
        Rate rate = new Rate(new Rate.RatePK(person, song), 4);
        rateDAO.save(rate);
        rateDAO.delete(rate);
        rate = rateDAO.find(rate.getId());

        Assert.assertNull("Оценка удалена", rate);
    }

}
