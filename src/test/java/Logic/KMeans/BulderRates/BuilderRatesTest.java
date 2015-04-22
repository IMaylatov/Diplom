package Logic.KMeans.BulderRates;

import com.IMaylatov.Recommend.Logic.KMeans.BuilderRates.BuilderRates;
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

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class BuilderRatesTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDao clusterDAO;
    @Autowired
    private SongDao songDAO;
    @Autowired
    private PersonDao persoDAO;
    @Autowired
    private BuilderRates builderRates;

    @Test
    public void spreadRate(){
        Cluster cluster = new Cluster();
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();
        songDAO.save(song1);
        songDAO.save(song2);
        songDAO.save(song3);

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();

        person1.getRates().put(song1, 3);

        person2.getRates().put(song1, 2);
        person2.getRates().put(song2, 4);

        person3.getRates().put(song1, 4);
        person3.getRates().put(song2, 4);

        person4.getRates().put(song1, 1);
        person4.getRates().put(song2, 1);
        person4.getRates().put(song3, 5);

        persoDAO.save(person1);
        persoDAO.save(person2);
        persoDAO.save(person3);
        persoDAO.save(person4);

        cluster.getPersons().add(person1);
        cluster.getPersons().add(person2);
        cluster.getPersons().add(person3);
        cluster.getPersons().add(person4);

        clusterDAO.save(cluster);

        clusterDAO.flush();

        builderRates.build(cluster);

        clusterDAO.save(cluster);

        Assert.assertEquals(3, (int)cluster.getRates().get(song1));
        Assert.assertEquals(3, (int)cluster.getRates().get(song2));
        Assert.assertEquals(3, (int)cluster.getRates().get(song3));
    }
}
