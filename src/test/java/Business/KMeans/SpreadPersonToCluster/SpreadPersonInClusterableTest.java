package Business.KMeans.SpreadPersonToCluster;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.BuilderRates;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInClusterable;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInCluster;
import com.IMaylatov.Recommend.Business.Metric.Euclid;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
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
public class SpreadPersonInClusterableTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SongDAO songDAO;

    @Test
    public void simpleSpreadTest(){
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        SpreadPersonInClusterable spread = new SpreadPersonInCluster();
        List<Cluster> clusters = spread.simpleSpread(persons, 3, new BuilderRates());
        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(0).getId()));
        Assert.assertNotNull(clusters.get(1).getPerson(persons.get(1).getId()));
        Assert.assertNotNull(clusters.get(2).getPerson(persons.get(2).getId()));
        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(3).getId()));
        Assert.assertNotNull(clusters.get(1).getPerson(persons.get(4).getId()));
        Assert.assertNotNull(clusters.get(2).getPerson(persons.get(5).getId()));
        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(6).getId()));
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

        persons.get(0).addRate(songs.get(0), 3);
        persons.get(0).addRate(songs.get(2), 3);
        persons.get(0).addRate(songs.get(4), 2);
        persons.get(0).addRate(songs.get(9), 3);
        persons.get(0).addRate(songs.get(11), 1);

        persons.get(1).addRate(songs.get(1), 5);
        persons.get(1).addRate(songs.get(3), 3);
        persons.get(1).addRate(songs.get(4), 5);
        persons.get(1).addRate(songs.get(5), 5);
        persons.get(1).addRate(songs.get(7), 3);

        persons.get(2).addRate(songs.get(0), 3);
        persons.get(2).addRate(songs.get(2), 1);
        persons.get(2).addRate(songs.get(4), 4);
        persons.get(2).addRate(songs.get(6), 4);
        persons.get(2).addRate(songs.get(9), 2);

        persons.get(3).addRate(songs.get(0), 3);
        persons.get(3).addRate(songs.get(1), 3);
        persons.get(3).addRate(songs.get(3), 2);
        persons.get(3).addRate(songs.get(8), 2);
        persons.get(3).addRate(songs.get(11), 1);

        persons.get(4).addRate(songs.get(1), 2);
        persons.get(4).addRate(songs.get(2), 2);
        persons.get(4).addRate(songs.get(5), 1);
        persons.get(4).addRate(songs.get(6), 2);
        persons.get(4).addRate(songs.get(9), 4);

        persons.get(5).addRate(songs.get(0), 5);
        persons.get(5).addRate(songs.get(2), 4);
        persons.get(5).addRate(songs.get(4), 5);
        persons.get(5).addRate(songs.get(6), 3);
        persons.get(5).addRate(songs.get(11), 5);

        persons.get(6).addRate(songs.get(0), 4);
        persons.get(6).addRate(songs.get(2), 3);
        persons.get(6).addRate(songs.get(5), 4);
        persons.get(6).addRate(songs.get(9), 5);

        for(Person person : persons)
            personDAO.save(person);

        SpreadPersonInClusterable spread = new SpreadPersonInCluster();

        List<Cluster> clusters = new ArrayList<>();
        clusters.add(new Cluster());
        clusters.add(new Cluster());
        clusters.add(new Cluster());

        clusters.get(0).addRate(songs.get(0), 1);
        clusters.get(0).addRate(songs.get(3), 4);
        clusters.get(0).addRate(songs.get(4), 3);
        clusters.get(0).addRate(songs.get(6), 4);
        clusters.get(0).addRate(songs.get(8), 4);
        clusters.get(0).addRate(songs.get(10), 5);
        clusters.get(0).addRate(songs.get(11), 3);

        clusters.get(1).addRate(songs.get(1), 5);
        clusters.get(1).addRate(songs.get(3), 3);
        clusters.get(1).addRate(songs.get(5), 3);
        clusters.get(1).addRate(songs.get(6), 2);
        clusters.get(1).addRate(songs.get(8), 1);
        clusters.get(1).addRate(songs.get(10), 4);
        clusters.get(1).addRate(songs.get(11), 5);

        clusters.get(2).addRate(songs.get(0), 5);
        clusters.get(2).addRate(songs.get(1), 2);
        clusters.get(2).addRate(songs.get(2), 3);
        clusters.get(2).addRate(songs.get(4), 2);
        clusters.get(2).addRate(songs.get(5), 5);
        clusters.get(2).addRate(songs.get(7), 2);
        clusters.get(2).addRate(songs.get(9), 5);
        clusters.get(2).addRate(songs.get(11), 1);

        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        persons = spread.distanceSpread(clusters, persons, new Euclid());

        Assert.assertEquals(clusters.get(2), persons.get(0).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(1).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(2).getCluster());
        Assert.assertEquals(clusters.get(2), persons.get(3).getCluster());
        Assert.assertEquals(clusters.get(0), persons.get(4).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(5).getCluster());
        Assert.assertEquals(clusters.get(1), persons.get(6).getCluster());
    }
}
