package Logic.DAO;

import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Song.SongPredicateDAO;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.webapp.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
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
 * date: 15.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SongPredicateTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SongPredicateDAO songPredicateDAO;

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);

        SongPredicate songPredicate = new SongPredicate(new PairKey<>(song, cluster), 3.5f);
        songPredicateDAO.save(songPredicate);

        songPredicate = (SongPredicate) session.get(SongPredicate.class, songPredicate.getId());
        Assert.assertNotNull(songPredicate);
        Assert.assertEquals(3.5f, songPredicate.getValue(), 0.001f);
    }

    @Test
    public void findTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);

        SongPredicate songPredicate = new SongPredicate(new PairKey<>(song, cluster), 3.5f);
        session.save(songPredicate);

        songPredicate = songPredicateDAO.find(songPredicate.getId());
        Assert.assertNotNull(songPredicate);
        Assert.assertEquals(3.5f, songPredicate.getValue(), 0.001f);
    }

    @Test
    public void updateTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);

        SongPredicate songPredicate = new SongPredicate(new PairKey<>(song, cluster), 3.5f);
        session.save(songPredicate);

        songPredicate.setValue(4f);
        songPredicateDAO.update(songPredicate);

        songPredicate = (SongPredicate) session.get(SongPredicate.class, songPredicate.getId());
        Assert.assertNotNull(songPredicate);
        Assert.assertEquals(4f, songPredicate.getValue(), 0.001f);
    }

    @Test
    public void deleteTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);

        SongPredicate songPredicate = new SongPredicate(new PairKey<>(song, cluster), 3.5f);
        session.save(songPredicate);

        songPredicateDAO.delete(songPredicate);

        songPredicate = (SongPredicate) session.get(SongPredicate.class, songPredicate.getId());
        Assert.assertNull(songPredicate);
    }

    @Test
    public void listTest(){
        List<SongPredicate> songPredicates = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Cluster cluster = new Cluster();
            session.save(cluster);
            Song song = new Song();
            session.save(song);
            SongPredicate songPredicate = new SongPredicate(new PairKey<>(song, cluster), 3.5f);
            session.save(songPredicate);
            songPredicates.add(songPredicate);
        }
        List<SongPredicate> songPredicatesFind = songPredicateDAO.list();

        Assert.assertEquals(songPredicates.size(), songPredicatesFind.size());
        for (int i = 0; i < songPredicates.size(); i++)
            Assert.assertEquals(songPredicates.get(0).getId(), songPredicatesFind.get(0).getId());
    }
}
