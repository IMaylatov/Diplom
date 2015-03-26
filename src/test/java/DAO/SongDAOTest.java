package DAO;

import com.IMaylatov.Recommend.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Model.Person;
import com.IMaylatov.Recommend.Model.Rate;
import com.IMaylatov.Recommend.Model.Song;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;


/**
 * Тест для проверки SongDAO
 */
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SongDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;

    @Test
    public void saveTest() {
        int countSongBeforeAdd = songDAO.list().size();
        Song song = new Song();
        songDAO.save(song);
        List<Song> songList = songDAO.list();
        Assert.assertEquals("Песня добавлена", countSongBeforeAdd + 1, songList.size());
        Assert.assertTrue("Созданная песня есть в БД", songList.contains(song));
    }

    @Test
    public void findTest(){
        Song song = new Song();
        songDAO.save(song);
        Assert.assertEquals("Песня найдена", song.getId(), songDAO.find(song.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Song song = new Song();
        songDAO.save(song);
        int countSongBeforeDelete = songDAO.list().size();
        songDAO.delete(song);
        Assert.assertEquals("Песня удалена", countSongBeforeDelete - 1, songDAO.list().size());
    }
}
