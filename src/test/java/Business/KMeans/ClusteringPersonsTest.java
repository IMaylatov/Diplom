package Business.KMeans;

import com.IMaylatov.Recommend.Business.KMeans.ClusteringPersons;
import com.IMaylatov.Recommend.Business.KMeans.FormRate.FormingRateInCluster;
import com.IMaylatov.Recommend.Business.KMeans.MoverCluster.MoverCluster;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonInCluster;
import com.IMaylatov.Recommend.Business.Metric.Euclid;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.RatePerson.RatePersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
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
 * date: 13.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ClusteringPersonsTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusteringPersons clusteringPersons;
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void spreadTest(){
        List<Song> songs = new ArrayList<>();
        for (int i = 0; i < 9; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            persons.add(new Person());

        persons.get(0).addRate(songs.get(0), 4);
        persons.get(0).addRate(songs.get(1), 1);
        persons.get(0).addRate(songs.get(3), 2);
        persons.get(0).addRate(songs.get(5), 2);
        persons.get(0).addRate(songs.get(6), 4);
        persons.get(0).addRate(songs.get(7), 5);
        persons.get(0).addRate(songs.get(8), 3);

        persons.get(1).addRate(songs.get(0), 4);
        persons.get(1).addRate(songs.get(1), 4);
        persons.get(1).addRate(songs.get(2), 4);
        persons.get(1).addRate(songs.get(4), 2);
        persons.get(1).addRate(songs.get(5), 2);
        persons.get(1).addRate(songs.get(7), 5);

        persons.get(2).addRate(songs.get(0), 4);
        persons.get(2).addRate(songs.get(2), 4);
        persons.get(2).addRate(songs.get(5), 5);
        persons.get(2).addRate(songs.get(8), 4);

        persons.get(3).addRate(songs.get(1), 5);
        persons.get(3).addRate(songs.get(2), 4);
        persons.get(3).addRate(songs.get(3), 3);
        persons.get(3).addRate(songs.get(4), 2);
        persons.get(3).addRate(songs.get(5), 3);
        persons.get(3).addRate(songs.get(6), 3);
        persons.get(3).addRate(songs.get(7), 4);
        persons.get(3).addRate(songs.get(8), 4);

        persons.get(4).addRate(songs.get(0), 5);
        persons.get(4).addRate(songs.get(1), 2);
        persons.get(4).addRate(songs.get(3), 5);
        persons.get(4).addRate(songs.get(5), 1);
        persons.get(4).addRate(songs.get(7), 2);

        for (Person person : persons)
            personDAO.save(person);

        clusteringPersons.spread(4, new Euclid(), new SpreadPersonInCluster(),
                new FormingRateInCluster(), new MoverCluster());

        int c = 0;
    }
}
