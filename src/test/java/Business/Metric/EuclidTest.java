package Business.Metric;

import com.IMaylatov.Recommend.Business.Metric.Euclid;
import com.IMaylatov.Recommend.Business.Metric.Metric;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
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
public class EuclidTest {
    @Autowired
    SongDAO songDAO;

    /**
     * У пользователей нет общих оценок
     */
    @Test
    public void compareExceptionNotCommonTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        for (int i = 0; i < 2; i++) {
            Song song = new Song();
            songDAO.save(song);
            person1.addRate(song, 5);
        }
        for (int i = 0; i < 3; i++){
            Song song = new Song();
            songDAO.save(song);
            person2.addRate(song, 4);
        }

        Metric metric = new Euclid();
        try{
            double distance = metric.compare(person1, person2);
            Assert.assertTrue("У пользователей нет общих оценок", false);
        }catch (IllegalArgumentException e){}
    }

    @Test
    public void compareResultTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        for (int i = 0; i < 5; i++){
            Song song = new Song();
            songDAO.save(song);
            person1.addRate(song, 4);
            person2.addRate(song, 5);
        }

        for (int i = 0; i < 3; i++){
            Song song = new Song();
            songDAO.save(song);
            person1.addRate(song, 3);
        }
        for (int i = 0; i < 2; i++){
            Song song = new Song();
            songDAO.save(song);
            person2.addRate(song, 2);
        }

        Metric metric = new Euclid();
        Assert.assertEquals("Расстояние между польлзователями", Math.sqrt(5), metric.compare(person1, person2), 0.001);
    }
}
