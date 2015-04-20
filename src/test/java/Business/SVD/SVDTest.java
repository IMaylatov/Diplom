package Business.SVD;

import com.IMaylatov.Recommend.Business.SVD.SVD;
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
 * date: 17.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SVDTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private SVD svd;

    @Test
    public void calculatePredicate(){
        List<Song> songs = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        persons.get(0).addRate(songs.get(1), 4);
        persons.get(0).addRate(songs.get(3), 3);

        persons.get(1).addRate(songs.get(0), 5);
        persons.get(1).addRate(songs.get(2), 4);
        persons.get(1).addRate(songs.get(3), 5);

        persons.get(2).addRate(songs.get(1), 2);
        persons.get(2).addRate(songs.get(4), 3);

        persons.get(3).addRate(songs.get(0), 1);
        persons.get(3).addRate(songs.get(2), 2);
        persons.get(3).addRate(songs.get(3), 4);
        persons.get(3).addRate(songs.get(4), 3);

        svd.calculatePredicate();

        Assert.assertEquals(0.0183f, persons.get(0).getPredicate(), 0.01f);
        Assert.assertEquals(0.1675f, persons.get(1).getPredicate(), 0.01f);
        Assert.assertEquals(-0.064f, persons.get(2).getPredicate(), 0.01f);
        Assert.assertEquals(-0.1199f, persons.get(3).getPredicate(), 0.01f);

        Cluster cluster = persons.get(0).getCluster();
        Assert.assertEquals(-0.0515f, songs.get(0).getPredicate(cluster).getValue(), 0.01f);
        Assert.assertEquals(-0.0442f, songs.get(1).getPredicate(cluster).getValue(), 0.01f);
        Assert.assertEquals(-0.0514f, songs.get(2).getPredicate(cluster).getValue(), 0.01f);
        Assert.assertEquals(0.1713f, songs.get(3).getPredicate(cluster).getValue(), 0.01f);
        Assert.assertEquals(-0.0332f, songs.get(4).getPredicate(cluster).getValue(), 0.01f);
    }
}
