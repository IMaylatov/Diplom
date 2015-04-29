package Logic.KMeans.ClusteringPerson;

import com.IMaylatov.Recommend.Logic.KMeans.ClusteringPerson.ClusteringPersons;
import com.IMaylatov.Recommend.Logic.Metric.Euclid;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Song.SongDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
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
    private SongDao songDAO;
    @Autowired
    private PersonDao personDAO;

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

        persons.get(0).getRates().put(songs.get(0), 4);
        persons.get(0).getRates().put(songs.get(1), 1);
        persons.get(0).getRates().put(songs.get(3), 2);
        persons.get(0).getRates().put(songs.get(5), 2);
        persons.get(0).getRates().put(songs.get(6), 4);
        persons.get(0).getRates().put(songs.get(7), 5);
        persons.get(0).getRates().put(songs.get(8), 3);

        persons.get(1).getRates().put(songs.get(0), 4);
        persons.get(1).getRates().put(songs.get(1), 4);
        persons.get(1).getRates().put(songs.get(2), 4);
        persons.get(1).getRates().put(songs.get(4), 2);
        persons.get(1).getRates().put(songs.get(5), 2);
        persons.get(1).getRates().put(songs.get(7), 5);

        persons.get(2).getRates().put(songs.get(0), 4);
        persons.get(2).getRates().put(songs.get(2), 4);
        persons.get(2).getRates().put(songs.get(5), 5);
        persons.get(2).getRates().put(songs.get(8), 4);

        persons.get(3).getRates().put(songs.get(1), 5);
        persons.get(3).getRates().put(songs.get(2), 4);
        persons.get(3).getRates().put(songs.get(3), 3);
        persons.get(3).getRates().put(songs.get(4), 2);
        persons.get(3).getRates().put(songs.get(5), 3);
        persons.get(3).getRates().put(songs.get(6), 3);
        persons.get(3).getRates().put(songs.get(7), 4);
        persons.get(3).getRates().put(songs.get(8), 4);

        persons.get(4).getRates().put(songs.get(0), 5);
        persons.get(4).getRates().put(songs.get(1), 2);
        persons.get(4).getRates().put(songs.get(3), 5);
        persons.get(4).getRates().put(songs.get(5), 1);
        persons.get(4).getRates().put(songs.get(7), 2);

        for (Person person : persons)
            personDAO.save(person);

        clusteringPersons.clustering(persons, 4, new Euclid());
    }
}
