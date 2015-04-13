package Business.KMeans.MoverCluster;

import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
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
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class MoverClusterTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void moveTest(){
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        Cluster cluster = new Cluster();
        cluster.addRate(songs.get(0), 2);
        cluster.addRate(songs.get(1), 4);
        cluster.addRate(songs.get(3), 5);
        cluster.addRate(songs.get(4), 3);
        cluster.addRate(songs.get(5), 4);

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        person1.addRate(songs.get(0), 4);
        person1.addRate(songs.get(1), 5);
        person1.addRate(songs.get(2), 3);
        person1.addRate(songs.get(4), 2);

        person2.addRate(songs.get(0), 3);
        person2.addRate(songs.get(1), 4);
        person2.addRate(songs.get(3), 2);
        person2.addRate(songs.get(5), 3);

        person3.addRate(songs.get(0), 5);
        person3.addRate(songs.get(1), 4);
        person3.addRate(songs.get(2), 3);
        person3.addRate(songs.get(4), 4);
        person3.addRate(songs.get(5), 4);

        personDAO.save(person1);
        personDAO.save(person2);
        personDAO.save(person3);

        cluster.addPerson(person1);
        cluster.addPerson(person2);
        cluster.addPerson(person3);

        clusterDAO.save(cluster);

        MoverCluster mover = new MoverCluster();
        mover.move(cluster);

        Assert.assertEquals(4, cluster.getRate(songs.get(0)).getValue());
        Assert.assertEquals(4, cluster.getRate(songs.get(1)).getValue());
        Assert.assertEquals(3, cluster.getRate(songs.get(2)).getValue());
        Assert.assertEquals(2, cluster.getRate(songs.get(3)).getValue());
        Assert.assertEquals(3, cluster.getRate(songs.get(4)).getValue());
        Assert.assertEquals(3, cluster.getRate(songs.get(5)).getValue());
    }
}
