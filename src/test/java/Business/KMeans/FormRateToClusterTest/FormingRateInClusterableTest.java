package Business.KMeans.FormRateToClusterTest;

import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInClusterable;
import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInCluster;
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

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class FormingRateInClusterableTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO persoDAO;

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

        person1.addRate(song1, 3);

        person2.addRate(song1, 2);
        person2.addRate(song2, 4);

        person3.addRate(song1, 4);
        person3.addRate(song2, 4);

        person4.addRate(song1, 1);
        person4.addRate(song2, 1);
        person4.addRate(song3, 5);

        persoDAO.save(person1);
        persoDAO.save(person2);
        persoDAO.save(person3);
        persoDAO.save(person4);

        cluster.addPerson(person1);
        cluster.addPerson(person2);
        cluster.addPerson(person3);
        cluster.addPerson(person4);

        clusterDAO.save(cluster);

        FormingRateInClusterable spreadRateToCluster = new FormingRateInCluster();
        spreadRateToCluster.form(cluster);

        clusterDAO.save(cluster);

        Assert.assertEquals(3, cluster.getRate(song1).getValue());
        Assert.assertEquals(4, cluster.getRate(song2).getValue());
        Assert.assertEquals(5, cluster.getRate(song3).getValue());
    }
}
