package Logic.SVD;

import com.IMaylatov.Recommend.Logic.SVD.SVD;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SVDTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private SongDao songDAO;
    @Autowired
    private SVD svd;

    @Test
    public void calculatePredicate(){
        List<Song> songs = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        persons.get(0).getRates().put(songs.get(1), 4);
        persons.get(0).getRates().put(songs.get(3), 3);

        persons.get(1).getRates().put(songs.get(0), 5);
        persons.get(1).getRates().put(songs.get(2), 4);
        persons.get(1).getRates().put(songs.get(3), 5);

        persons.get(2).getRates().put(songs.get(1), 2);
        persons.get(2).getRates().put(songs.get(4), 3);

        persons.get(3).getRates().put(songs.get(0), 1);
        persons.get(3).getRates().put(songs.get(2), 2);
        persons.get(3).getRates().put(songs.get(3), 4);
        persons.get(3).getRates().put(songs.get(4), 3);

        svd.calculatePredicate();

        Assert.assertEquals(0.0183f, persons.get(0).getPredicate(), 0.01f);
        Assert.assertEquals(0.1675f, persons.get(1).getPredicate(), 0.01f);
        Assert.assertEquals(-0.064f, persons.get(2).getPredicate(), 0.01f);
        Assert.assertEquals(-0.1199f, persons.get(3).getPredicate(), 0.01f);

        Cluster cluster = persons.get(0).getCluster();
        Assert.assertEquals(-0.0515f, songs.get(0).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0442f, songs.get(1).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0514f, songs.get(2).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(0.1713f, songs.get(3).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0332f, songs.get(4).getPredicates().get(cluster), 0.01f);
    }
}
