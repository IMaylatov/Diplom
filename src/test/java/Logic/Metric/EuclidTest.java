package Logic.Metric;

import com.IMaylatov.Recommend.Logic.Metric.Euclid;
import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.junit.Test;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class EuclidTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    SongDao songDao;

    /**
     * У пользователей нет общих оценок
     */
    @Test
    public void compareExceptionNotCommonTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        for (int i = 0; i < 2; i++) {
            Song song = new Song();
            songDao.save(song);
            person1.getRates().put(song, 5);
        }
        for (int i = 0; i < 3; i++){
            Song song = new Song();
            songDao.save(song);
            person2.getRates().put(song, 4);
        }

        Metric metric = new Euclid();
        double distance = metric.compare(person1, person2);
        Assert.assertEquals("У пользователей нет общих оценок", Double.MAX_VALUE, distance, 0.001);
    }

    @Test
    public void compareResultTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        for (int i = 0; i < 5; i++){
            Song song = new Song();
            songDao.save(song);
            person1.getRates().put(song, 4);
            person2.getRates().put(song, 5);
        }

        for (int i = 0; i < 3; i++){
            Song song = new Song();
            songDao.save(song);
            person1.getRates().put(song, 3);
        }
        for (int i = 0; i < 2; i++){
            Song song = new Song();
            songDao.save(song);
            person2.getRates().put(song, 2);
        }

        Metric metric = new Euclid();
        Assert.assertEquals("Расстояние между польлзователями", Math.sqrt(5), metric.compare(person1, person2), 0.001);
    }
}
