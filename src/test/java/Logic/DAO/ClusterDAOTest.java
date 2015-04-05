package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
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
 * date: 04.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ClusterDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private ClusterDAO clusterDAO;
    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Before
    public void init(){
        session = sessionFactory.getCurrentSession();
    }

    @Test
    public void saveTest() {
        Cluster cluster = new Cluster();
        clusterDAO.save(cluster);
        Cluster findCluster = (Cluster) session.get(Cluster.class, cluster.getId());
        Assert.assertNotNull("Кластер добавлен", findCluster);
        Assert.assertTrue("Созданный кластер есть в БД", cluster.getId() == findCluster.getId());
    }

    @Test
    public void findTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Assert.assertEquals("Кластер добавлен", cluster.getId(), clusterDAO.find(cluster.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        clusterDAO.delete(cluster);
        cluster = (Cluster) session.get(Cluster.class, cluster.getId());
        Assert.assertNull("Кластер удален", cluster);
    }

    @Test
    public void listTest(){
        List<Cluster> clusterList = new ArrayList<Cluster>();
        for (int i = 0; i < 7; i++) {
            Cluster cluster = new Cluster();
            clusterList.add(cluster);
            session.save(cluster);
        }
        Assert.assertTrue("Кластеры найдены", clusterDAO.list().containsAll(clusterList));
    }
}
