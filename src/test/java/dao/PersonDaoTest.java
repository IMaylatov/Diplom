package dao;

import com.IMaylatov.recommend.webapp.dao.model.cluster.ClusterDao;
import com.IMaylatov.recommend.webapp.dao.model.person.PersonDao;
import com.IMaylatov.recommend.webapp.dao.model.song.SongDao;
import com.IMaylatov.recommend.webapp.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class PersonDaoTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDao clusterDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private SongDao songDao;

    @Test
    public void saveTest(){
        Person person = new Person();
        person.setPredicate(3l);
        personDao.save(person);
    }
}
