package Logic.Model;

import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson.RatePersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 31.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private RatePersonDAO ratePersonDAO;

    @Test
    public void rateListTest(){
        List<Song> songs = new ArrayList<Song>();
        for (int i = 0; i < 7; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        Person person = new Person();
        for (int i = 0; i < 7; i++) {
            person.addRate(songs.get(i), i % 5 + 1);
        }

        personDAO.save(person);
        person = personDAO.find(person.getId());

        for (int i = 0; i < 7; i++) {
            Assert.assertEquals("Пользователю удалось поставить оценку",
                    person.getRate(songs.get(i)).getValue(), i % 5 + 1);
        }
        Song songWithoutRate = new Song();
        songDAO.save(songWithoutRate);
        Assert.assertNull("Пользователь не ставил оценки этой песне", person.getRate(songWithoutRate));
    }

    @Test
    public void addRateTest(){
        Person person = new Person();
        Song song = new Song();
        person.addRate(song, 4);
        Assert.assertEquals("Пользователь поставил оценку 4", 4, person.iteratorRates().next().getValue());
        person.addRate(song, 5);
        Assert.assertEquals("Пользователь обновил оценку 5", 5, person.iteratorRates().next().getValue());
        Assert.assertEquals("Значение оценки обновлено", 5, person.getRate(song).getValue());
    }

    @Test
    public void removeRateTest(){
        Person person = new Person();
        Song song = new Song();
        songDAO.save(song);
        person.addRate(song, 5);
        personDAO.save(person);
        Assert.assertNotNull("Пользователь поставил оценку песне", person.getRate(song));
        person = personDAO.find(person.getId());
        person.removeRate(song);
        personDAO.save(person);
        Assert.assertNull("Пользователь удалил оценку у песни", person.getRate(song));
    }


    @Test
    public void clusterTest(){
        Person person = new Person();
        Cluster cluster = new Cluster();
        clusterDAO.save(cluster);
        person.setCluster(cluster);
        personDAO.save(person);
        person = personDAO.find(person.getId());
        Assert.assertEquals("Пользователь выстваил кластер", person.getCluster().getId(), cluster.getId());
    }
}
