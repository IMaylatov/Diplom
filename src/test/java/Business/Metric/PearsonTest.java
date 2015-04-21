package Business.Metric;

import com.IMaylatov.Recommend.Logic.Metric.Metric;
import com.IMaylatov.Recommend.Logic.Metric.Pearson;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.DoubleSummaryStatistics;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PearsonTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    SongDao songDAO;

    @Test
    public void compareNotCommonTest(){
        Person person1 = new Person();
        Person person2 = new Person();

        for (int i = 0; i < 3; i++){
            Song song = new Song();
            songDAO.save(song);
            person1.getRates().put(song, 4);
        }
        for (int i = 0; i < 2; i++){
            Song song = new Song();
            songDAO.save(song);
            person2.getRates().put(song, 4);
        }

        Metric metric = new Pearson();
        double distance = metric.compare(person1, person2);
        Assert.assertEquals("У пользователей нет общих оценок или их количество не подходит для вычисления",
                Double.MAX_VALUE, distance, 0.001);
    }

    @Test
    public void compareCommonTest(){
        Metric metric = new Pearson();

        Person person1 = new Person();
        Person person2 = new Person();
        int[] ratesPerson1 = new int[]{5, 4, 5};
        int[] ratesPerson2 = new int[]{4, 3, 4};
        fillPerson(person1, person2, ratesPerson1, ratesPerson2);

        Assert.assertEquals("Расстояние между пользователями", 1, metric.compare(person1, person2), 0.001);

        person1 = new Person();
        person2 = new Person();
        ratesPerson1 = new int[]{2, 1, 3, 3, 3, 4, 3};
        ratesPerson2 = new int[]{1, 2, 2, 2, 1, 2, 2};
        fillPerson(person1, person2, ratesPerson1, ratesPerson2);

        Assert.assertEquals("Расстояние между пользователями", 0.1539, metric.compare(person1, person2), 0.001);
    }

    /**
     * Заполнить пользователя оценками для общих песен
     */
    private void fillPerson(Person person1, Person person2, int[] rateValue1, int[] rateValue2){
        for (int i = 0; (i < rateValue1.length) && (i < rateValue2.length); i++){
            Song song = new Song();
            songDAO.save(song);
            person1.getRates().put(song, rateValue1[i]);
            person2.getRates().put(song, rateValue2[i]);
        }
    }
}
