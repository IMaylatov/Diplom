package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
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
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest() {
        Song song = new Song();
        songDAO.save(song);
        Song findSong = (Song) session.get(Song.class, song.getId());
        Assert.assertNotNull("Песня добавлена", findSong);
        Assert.assertTrue("Созданная песня есть в БД", song.equals(findSong));
    }

    @Test
    public void findTest(){
        Song song = new Song();
        session.save(song);
        Assert.assertEquals("Песня найдена", song.getId(), songDAO.find(song.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Song song = new Song();
        session.save(song);
        songDAO.delete(song);
        song = (Song) session.get(Song.class, song.getId());
        Assert.assertNull("Песня удалена", song);
    }

    @Test
    public void listTest(){
        List<Song> songList = new ArrayList<Song>();
        for (int i = 0; i < 7; i++) {
            Song song = new Song();
            songList.add(song);
            session.save(song);
        }
        Assert.assertTrue("Песни найдены", songDAO.list().containsAll(songList));
    }
}
