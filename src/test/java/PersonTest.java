import com.IMaylatov.Recommend.webapp.DAO.Model.Cluster.ClusterDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Person.PersonPredicateDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
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
 * date: 22.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDao personDao;
    @Autowired
    private PersonPredicateDao personPredicateDao;
    @Autowired
    private ClusterDao clusterDao;

    @Test
    public void saveTest(){
        Person person = new Person();
        personDao.save(person);

        Person personFind = personDao.find(person.getId());
        Assert.assertEquals(person.getId(), personFind.getId());
    }
}
