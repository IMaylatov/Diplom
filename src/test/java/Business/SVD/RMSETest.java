package Business.SVD;

import com.IMaylatov.Recommend.Business.SVD.RMSE.RMSE;
import com.IMaylatov.Recommend.Business.SVD.RMSE.RMSEImpl;
import com.IMaylatov.Recommend.Business.SVD.SVD;
import com.IMaylatov.Recommend.Business.LoaderData.LoaderData;
import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.HashMap;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml" })
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
public class RMSETest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private LoaderData loaderData;
    @Autowired
    private SVD svd;

    @Test
    public void calcualteErrorTest(){
        HashMap<String, String> files = new HashMap<>();
        files.put("person", "data/person.dat");
        files.put("song", "data/song.dat");
        files.put("rate", "data/ratings.dat");
        loaderData.loadAll(files);

        svd.calculatePredicate();

//        List<RatePerson> ratesTest = loaderData.loadTestRate("data/testRate.dat");
//
//        RMSE rmse = new RMSEImpl();
//        double error = rmse.calculateError(ratesTest);
//
//        Assert.assertTrue(error < 2);
    }
}
