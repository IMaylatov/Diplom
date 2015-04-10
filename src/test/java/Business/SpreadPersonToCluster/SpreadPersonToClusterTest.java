package Business.SpreadPersonToCluster;

import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonToCluster;
import com.IMaylatov.Recommend.Business.KMeans.SpreadPeople.SpreadPersonToClusterImpl;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
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
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class SpreadPersonToClusterTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private ClusterDAO clusterDAO;

    @Test
    public void simpleSpreadTest(){
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            Person person = new Person();
            personDAO.save(person);
            persons.add(person);
        }

        SpreadPersonToCluster spread = new SpreadPersonToClusterImpl();
        List<Cluster> clusters = spread.simpleSpread(persons, 3);
        for(Cluster cluster : clusters)
            clusterDAO.save(cluster);

        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(0).getId()));
        Assert.assertNotNull(clusters.get(1).getPerson(persons.get(1).getId()));
        Assert.assertNotNull(clusters.get(2).getPerson(persons.get(2).getId()));
        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(3).getId()));
        Assert.assertNotNull(clusters.get(1).getPerson(persons.get(4).getId()));
        Assert.assertNotNull(clusters.get(2).getPerson(persons.get(5).getId()));
        Assert.assertNotNull(clusters.get(0).getPerson(persons.get(6).getId()));
    }
}
