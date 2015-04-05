package Logic.DAO;

import com.IMaylatov.Recommend.Logic.DAO.Model.Cluster.RateCluster.RateClusterDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Cluster.RateCluster;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.junit.Assert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class RateClusterDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
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
    public void saveTest(){
        // ������ �� ����� ���� ���������, ���� ����� ��� �������� �� ����������
        // ��������� ������ � ������������� ��������� � ������
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);

        cluster = (Cluster) session.get(Cluster.class, cluster.getId());
        song = (Song) session.get(Song.class, song.getId());

        RateCluster rateCluster = new RateCluster(new RateCluster.PairKey(cluster, song), 3);
        rateClusterDAO.save(rateCluster);
        rateCluster = (RateCluster) session.get(RateCluster.class, rateCluster.getId());
        Assert.assertNotNull("���������� ������ ���������", rateCluster);
        Assert.assertEquals("���������� ������ ����������� �����", 3, rateCluster.getValue());

        // ��������� ������ � ������������ ���������, �� �� ������������ ������
        song = new Song();
        cluster = new Cluster();
        session.save(cluster);
        rateCluster = new RateCluster(new RateCluster.PairKey(cluster, song), 3);
        try{
            rateClusterDAO.save(rateCluster);
            Assert.assertTrue("������ �� ����� ���� ���������", false);
        }catch (Exception ex){}

        // ��������� ������ � �������������� ���������, �� � ������������ ������
        song = new Song();
        session.save(song);
        cluster = new Cluster();
        rateCluster = new RateCluster(new RateCluster.PairKey(cluster, song), 3);
        try{
            rateClusterDAO.save(rateCluster);
            Assert.assertTrue("������ �� ����� ���� ���������", false);
        }catch (Exception ex){}
    }

    @Test
    public void findTest(){
        Cluster cluster = new Cluster();
        Song song = new Song();
        RateCluster rate = new RateCluster(new RateCluster.PairKey(cluster, song), 4);
        session.save(rate);
        rate = rateClusterDAO.find(rate.getId());
        Assert.assertNotNull("������ �������", rate);
        Assert.assertEquals("������ ������� �����", 4, rate.getValue());
    }

    @Test
    public void updateTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);
        RateCluster rate = new RateCluster(new RateCluster.PairKey(cluster, song),4);
        session.save(rate);
        rate.setValue(5);
        rateClusterDAO.update(rate);
        rate = (RateCluster) session.get(RateCluster.class, rate.getId());
        Assert.assertNotNull("������ ���������", rate);
        Assert.assertEquals("�������� ������ ��������� ���������", 5, rate.getValue());
    }

    @Test
    public void deleteTest(){
        Cluster cluster = new Cluster();
        session.save(cluster);
        Song song = new Song();
        session.save(song);
        RateCluster rate = new RateCluster(new RateCluster.PairKey(cluster, song), 4);
        session.save(rate);
        rateClusterDAO.delete(rate);
        rate = (RateCluster) session.get(RateCluster.class, rate.getId());
        Assert.assertNull("������ �������", rate);
    }

    @Test
    public void listTest(){
        List<RateCluster> rateList = new ArrayList<RateCluster>();
        for (int i = 0; i < 7; i++) {
            Cluster cluster = new Cluster();
            session.save(cluster);
            Song song = new Song();
            session.save(song);

            RateCluster rate = new RateCluster(new RateCluster.PairKey(cluster, song), i % 5 + 1);
            rateList.add(rate);
            session.save(rate);
        }
        Assert.assertTrue("������ �������", rateClusterDAO.list().containsAll(rateList));
    }
}
