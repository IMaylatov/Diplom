package Model;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 31.03.2015.
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;

    @Test
    public void rateListTest(){
        List<Song> songs = new ArrayList<Song>();
        for (int i = 0; i < 7; i++){
            Song song = new Song();
            songDAO.save(song);
            songs.add(song);
        }

        Person person = new Person();
        for (int i = 0; i < 7; i++) {
            person.addRate(songs.get(i), i % 5 + 1);
        }

        personDAO.save(person);
        person = personDAO.find(person.getId());

        for (int i = 0; i < 7; i++) {
            Assert.assertEquals("Пользователю удалось поставить оценку",
                    person.getRate(songs.get(i)).getValue(), i % 5 + 1);
        }
        Song songWithoutRate = new Song();
        songDAO.save(songWithoutRate);
        Assert.assertNull("Пользователь не ставил оценки этой песне", person.getRate(songWithoutRate));
    }
}
