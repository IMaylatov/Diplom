package Logic.Model;

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
 * date: 04.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ClusterTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void rateClusterTest(){
        Cluster cluster = new Cluster();
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 7; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
            cluster.addRate(songs.get(i), i % 5 + 1);
        }

        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());

        for (int i = 0; i < 7; i++)
            Assert.assertEquals(cluster.getRate(songs.get(i)).getValue(), i % 5 + 1);

        Song songWithOutRate = new Song();
        songDAO.save(songWithOutRate);
        Assert.assertNull(cluster.getRate(songWithOutRate));
    }

    @Test
    public void addRateTest(){
        Cluster cluster = new Cluster();
        Song song = new Song();
        cluster.addRate(song, 4);
        Assert.assertEquals(4, cluster.iteratorRates().next().getValue());
        cluster.addRate(song, 5);
        Assert.assertEquals(5, cluster.iteratorRates().next().getValue());
    }

    @Test
    public void removeRateTest(){
        Cluster cluster = new Cluster();
        Song song = new Song();
        songDAO.save(song);
        cluster.addRate(song, 4);
        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());
        cluster.removeRate(song);
        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());
        Assert.assertNull("Кластерная оценка удалена", cluster.getRate(song));
    }

    @Test
    public void addPerson(){
        Cluster cluster = new Cluster();
        Person person = new Person();
        personDAO.save(person);
        cluster.addPerson(person);
        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());
        Assert.assertEquals(person.getId(), cluster.getPerson(person.getId()).getId());
    }

    @Test
    public void removePerson(){
        Cluster cluster = new Cluster();
        Person person = new Person();
        personDAO.save(person);
        cluster.addPerson(person);
        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());
        cluster.removePerson(person);
        clusterDAO.save(cluster);
        cluster = clusterDAO.find(cluster.getId());
        Assert.assertNull("Пользователь удален из кластера", cluster.getPerson(person.getId()));
    }
}
