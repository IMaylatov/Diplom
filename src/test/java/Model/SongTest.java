package Model;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Assert;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 31.03.2015.
 */

/**
 * Тест для проверки PersonDAO
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SongTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;

    @Test
    public void rateListTest(){
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 7; i++){
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        Song song = new Song();
        for (int i = 0; i < 7; i++) {
            song.addRate(persons.get(i), i % 5 + 1);
        }

        songDAO.save(song);
        song = songDAO.find(song.getId());

        for (int i = 0; i < 7; i++) {
            Assert.assertEquals("Для песни поставилась оценка",
                    song.getRate(persons.get(i)).getValue(), i % 5 + 1);
        }
        Person personWithoutRate = new Person();
        personDAO.save(personWithoutRate);
        Assert.assertNull("Для песни нет оценки", song.getRate(personWithoutRate));
    }
}
