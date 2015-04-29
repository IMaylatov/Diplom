package Logic.SVD;

import com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate.CalculaterPredicate;
import com.IMaylatov.Recommend.Logic.SVD.GradientDown.GradientDown;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
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
public class GradientDownTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private SongDao songDAO;
    @Autowired
    private ClusterDao clusterDAO;
    @Autowired
    private CalculaterPredicate calculaterPredicate;
    @Autowired
    private GradientDown gradientDown;

    @Test
    public void downTest(){
        List<Song> songs = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Person person = new Person();
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

        Cluster cluster = new Cluster();
        for(Person person : persons){
            personDAO.save(person);
            cluster.getPersons().add(person);
        }
        cluster.setSummaRate(36);
        cluster.setCountRate(11);
        clusterDAO.save(cluster);
        clusterDAO.flush();

        calculaterPredicate.calculate(cluster);

        gradientDown.down(cluster);

        Assert.assertEquals(0.0183f, persons.get(0).getPredicate(), 0.01f);
        Assert.assertEquals(0.1675f, persons.get(1).getPredicate(), 0.01f);
        Assert.assertEquals(-0.064f, persons.get(2).getPredicate(), 0.01f);
        Assert.assertEquals(-0.1199f, persons.get(3).getPredicate(), 0.01f);

        Assert.assertEquals(-0.0515f, songs.get(0).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0442f, songs.get(1).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0514f, songs.get(2).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(0.1713f, songs.get(3).getPredicates().get(cluster), 0.01f);
        Assert.assertEquals(-0.0332f, songs.get(4).getPredicates().get(cluster), 0.01f);
    }
}
