package Logic.Model;

import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
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
 * date: 15.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SongTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SongDAO songDAO;

    @Test
    public void getPredicate(){
        Song song = new Song();
        songDAO.save(song);
        Cluster cluster = new Cluster();
        clusterDAO.save(cluster);

        song.addPredicate(4f, cluster);
        songDAO.save(song);
        song = songDAO.find(song.getId());

        Assert.assertEquals(4f, song.getPredicate(cluster).getValue(), 0.001f);
        Assert.assertEquals(cluster.getId(), song.getPredicate(cluster).getCluster().getId());
    }

    @Test
    public void removePredicate(){
        Song song = new Song();
        songDAO.save(song);
        Cluster cluster = new Cluster();
        clusterDAO.save(cluster);

        song.addPredicate(4f, cluster);
        songDAO.save(song);
        song.removePredicate(cluster);
        songDAO.save(song);
        song = songDAO.find(song.getId());

        Assert.assertNull(song.getPredicate(cluster));
    }
}
