package Logic.KMeans.SpreadPerson;

import com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople.SpreadPerson;
import com.IMaylatov.Recommend.Logic.KMeans.SpreadPeople.SpreadPersonImpl;
import com.IMaylatov.Recommend.Logic.Metric.Euclid;
import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
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
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SpreadPersonTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDao personDAO;
    @Autowired
    private ClusterDao clusterDAO;
    @Autowired
    private SongDao songDAO;

    @Test
    public void simpleSpreadTest(){
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        SpreadPerson spreadPerson = new SpreadPersonImpl();
        List<Cluster> clusters = spreadPerson.evenlySpread(persons, 3);

        Assert.assertEquals(persons.get(0).getId(), clusters.get(0).getPersons().get(0).getId());
        Assert.assertEquals(persons.get(1).getId(), clusters.get(1).getPersons().get(0).getId());
        Assert.assertEquals(persons.get(2).getId(), clusters.get(2).getPersons().get(0).getId());
        Assert.assertEquals(persons.get(3).getId(), clusters.get(0).getPersons().get(1).getId());
        Assert.assertEquals(persons.get(4).getId(), clusters.get(1).getPersons().get(1).getId());
        Assert.assertEquals(persons.get(5).getId(), clusters.get(2).getPersons().get(1).getId());
        Assert.assertEquals(persons.get(6).getId(), clusters.get(0).getPersons().get(2).getId());
    }

    @Test
    public void distanceSpreadTest(){
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            Person person = new Person();
            persons.add(person);
        }

        persons.get(0).getRates().put(songs.get(0), 3);
        persons.get(0).getRates().put(songs.get(2), 3);
        persons.get(0).getRates().put(songs.get(4), 2);
        persons.get(0).getRates().put(songs.get(9), 3);
        persons.get(0).getRates().put(songs.get(11), 1);

        persons.get(1).getRates().put(songs.get(1), 5);
        persons.get(1).getRates().put(songs.get(3), 3);
        persons.get(1).getRates().put(songs.get(4), 5);
        persons.get(1).getRates().put(songs.get(5), 5);
        persons.get(1).getRates().put(songs.get(7), 3);

        persons.get(2).getRates().put(songs.get(0), 3);
        persons.get(2).getRates().put(songs.get(2), 1);
        persons.get(2).getRates().put(songs.get(4), 4);
        persons.get(2).getRates().put(songs.get(6), 4);
        persons.get(2).getRates().put(songs.get(9), 2);

        persons.get(3).getRates().put(songs.get(0), 3);
        persons.get(3).getRates().put(songs.get(1), 3);
        persons.get(3).getRates().put(songs.get(3), 2);
        persons.get(3).getRates().put(songs.get(8), 2);
        persons.get(3).getRates().put(songs.get(11), 1);

        persons.get(4).getRates().put(songs.get(1), 2);
        persons.get(4).getRates().put(songs.get(2), 2);
        persons.get(4).getRates().put(songs.get(5), 1);
        persons.get(4).getRates().put(songs.get(6), 2);
        persons.get(4).getRates().put(songs.get(9), 4);

        persons.get(5).getRates().put(songs.get(0), 5);
        persons.get(5).getRates().put(songs.get(2), 4);
        persons.get(5).getRates().put(songs.get(4), 5);
        persons.get(5).getRates().put(songs.get(6), 3);
        persons.get(5).getRates().put(songs.get(11), 5);

        persons.get(6).getRates().put(songs.get(0), 4);
        persons.get(6).getRates().put(songs.get(2), 3);
        persons.get(6).getRates().put(songs.get(5), 4);
        persons.get(6).getRates().put(songs.get(9), 5);

        for(Person person : persons)
            personDAO.save(person);

        List<Cluster> clusters = new ArrayList<>();
        clusters.add(new Cluster());
        clusters.add(new Cluster());
        clusters.add(new Cluster());

        clusters.get(0).getRates().put(songs.get(0), 1);
        clusters.get(0).getRates().put(songs.get(3), 4);
        clusters.get(0).getRates().put(songs.get(4), 3);
        clusters.get(0).getRates().put(songs.get(6), 4);
        clusters.get(0).getRates().put(songs.get(8), 4);
        clusters.get(0).getRates().put(songs.get(10), 5);
        clusters.get(0).getRates().put(songs.get(11), 3);

        clusters.get(1).getRates().put(songs.get(1), 5);
        clusters.get(1).getRates().put(songs.get(3), 3);
        clusters.get(1).getRates().put(songs.get(5), 3);
        clusters.get(1).getRates().put(songs.get(6), 2);
        clusters.get(1).getRates().put(songs.get(8), 1);
        clusters.get(1).getRates().put(songs.get(10), 4);
        clusters.get(1).getRates().put(songs.get(11), 5);

        clusters.get(2).getRates().put(songs.get(0), 5);
        clusters.get(2).getRates().put(songs.get(1), 2);
        clusters.get(2).getRates().put(songs.get(2), 3);
        clusters.get(2).getRates().put(songs.get(4), 2);
        clusters.get(2).getRates().put(songs.get(5), 5);
        clusters.get(2).getRates().put(songs.get(7), 2);
        clusters.get(2).getRates().put(songs.get(9), 5);
        clusters.get(2).getRates().put(songs.get(11), 1);

        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        SpreadPerson spreadPerson = new SpreadPersonImpl();
        spreadPerson.distanceSpread(clusters, persons, new Euclid());

        Assert.assertEquals(clusters.get(2), persons.get(0).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(1).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(2).getCluster());
        Assert.assertEquals(clusters.get(2), persons.get(3).getCluster());
        Assert.assertEquals(clusters.get(0), persons.get(4).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(5).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(6).getCluster());
    }
}
