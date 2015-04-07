package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.Cluster.ClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Cluster.RateCluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
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
    private SongDAO songDAO;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private RateClusterDAO rateClusterDAO;
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
        Assert.assertNotNull("������� ��������", findCluster);
        Assert.assertTrue("��������� ������� ���� � ��", cluster.getId() == findCluster.getId());
    }

    @Test
    public void findTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Assert.assertEquals("������� ��������", cluster.getId(), clusterDAO.find(cluster.getId()).getId());
    }

    @Test
    public void deleteTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        clusterDAO.delete(cluster);
        cluster = (Cluster) session.get(Cluster.class, cluster.getId());
        Assert.assertNull("������� ������", cluster);
    }

    @Test
    public void listTest(){
        List<Cluster> clusterList = new ArrayList<Cluster>();
        for (int i = 0; i < 7; i++) {
            Cluster cluster = new Cluster();
            clusterList.add(cluster);
            session.save(cluster);
        }
        Assert.assertTrue("�������� �������", clusterDAO.list().containsAll(clusterList));
    }

    @Test
    public void DeleteAllTest(){
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();
        songDAO.save(song1);
        songDAO.save(song2);
        songDAO.save(song3);

        Cluster cluster1 = new Cluster();
        Cluster cluster2 = new Cluster();
        Cluster cluster3 = new Cluster();

        cluster1.addRate(song1, 3);
        cluster1.addRate(song2, 4);
        cluster1.addRate(song3, 2);

        cluster2.addRate(song1, 5);
        cluster2.addRate(song2, 3);

        cluster3.addRate(song1, 2);

        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        Person person4 = new Person();
        Person person5 = new Person();
        Person person6 = new Person();

        personDAO.save(person1);
        personDAO.save(person2);
        personDAO.save(person3);
        personDAO.save(person4);
        personDAO.save(person5);
        personDAO.save(person6);

        cluster1.addPerson(person1);
        cluster1.addPerson(person2);
        cluster1.addPerson(person3);
        cluster2.addPerson(person4);
        cluster2.addPerson(person5);
        cluster3.addPerson(person6);

        clusterDAO.save(cluster1);
        clusterDAO.save(cluster2);
        clusterDAO.save(cluster3);

        Assert.assertEquals("�������� �����������", 3, clusterDAO.list().size());
        Assert.assertEquals("���������� ������ �����������", 6, rateClusterDAO.list().size());
        Assert.assertEquals("������������ �����������", 6, personDAO.list().size());

        clusterDAO.deleteAll();

        Assert.assertEquals("�������� ���������", 0, clusterDAO.list().size());
        Assert.assertEquals("���������� ������ ���������", 0, rateClusterDAO.list().size());
        Assert.assertEquals("������������ �� ���������", 6, personDAO.list().size());

        person1 = personDAO.find(person1.getId());
        person2 = personDAO.find(person2.getId());
        person3 = personDAO.find(person3.getId());
        person4 = personDAO.find(person4.getId());
        person5 = personDAO.find(person5.getId());
        person6 = personDAO.find(person6.getId());

        Assert.assertNull("������������ �� ����������� ��������", person1.getCluster());
        Assert.assertNull("������������ �� ����������� ��������", person2.getCluster());
        Assert.assertNull("������������ �� ����������� ��������", person3.getCluster());
        Assert.assertNull("������������ �� ����������� ��������", person4.getCluster());
        Assert.assertNull("������������ �� ����������� ��������", person5.getCluster());
        Assert.assertNull("������������ �� ����������� ��������", person6.getCluster());
    }
}
