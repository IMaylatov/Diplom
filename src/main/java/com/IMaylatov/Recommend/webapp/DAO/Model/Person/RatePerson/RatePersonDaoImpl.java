package com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.DAO.Model.Person.RatePerson.RatePersonDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.webapp.Model.Song;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("RatePersonDao")
public class RatePersonDaoImpl extends GenericDaoImpl<RatePerson, PairKey<Person, Song>> implements RatePersonDao {
    @Override
    public List<RatePerson> getRateForSongInCluster(Song song, Cluster cluster) {
        return list(Restrictions.sqlRestriction(
                String.format(
                        "SongId = %d and" +
                        " PersonId in " +
                        "(Select Id from Person where ClusterId = %d)"
                        , song.getId()
                        , cluster.getId())
        ));
    }
}
