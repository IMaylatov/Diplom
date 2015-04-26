package Logic.KMeans.MoverCluster;

import com.IMaylatov.Recommend.Logic.KMeans.MoverCluster.MoverCluster;
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
 * date: 13.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class MoverClusterTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private SongDao songDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MoverCluster moverCluster;

    @Test
    public void moveTest(){
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Song song = new Song();
            songDao.save(song);
            songs.add(song);
        }

        Cluster cluster = new Cluster();
        cluster.getRates().put(songs.get(0), 2);
        cluster.getRates().put(songs.get(1), 4);
        cluster.getRates().put(songs.get(3), 5);
        cluster.getRates().put(songs.get(4), 3);
        cluster.getRates().put(songs.get(5), 4);

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        person1.getRates().put(songs.get(0), 4);
        person1.getRates().put(songs.get(1), 5);
        person1.getRates().put(songs.get(2), 3);
        person1.getRates().put(songs.get(4), 2);

        person2.getRates().put(songs.get(0), 3);
        person2.getRates().put(songs.get(1), 4);
        person2.getRates().put(songs.get(3), 2);
        person2.getRates().put(songs.get(5), 3);

        person3.getRates().put(songs.get(0), 5);
        person3.getRates().put(songs.get(1), 4);
        person3.getRates().put(songs.get(2), 3);
        person3.getRates().put(songs.get(4), 4);
        person3.getRates().put(songs.get(5), 4);

        personDao.save(person1);
        personDao.save(person2);
        personDao.save(person3);

        cluster.getPersons().add(person1);
        cluster.getPersons().add(person2);
        cluster.getPersons().add(person3);

        clusterDao.save(cluster);

        clusterDao.flush();

        moverCluster.move(cluster);

        Assert.assertEquals(4, (int)cluster.getRates().get(songs.get(0)));
        Assert.assertEquals(4, (int)cluster.getRates().get(songs.get(1)));
        Assert.assertEquals(3, (int)cluster.getRates().get(songs.get(2)));
        Assert.assertEquals(2, (int)cluster.getRates().get(songs.get(3)));
        Assert.assertEquals(3, (int)cluster.getRates().get(songs.get(4)));
        Assert.assertEquals(3, (int)cluster.getRates().get(songs.get(5)));
    }
}
