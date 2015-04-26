package com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDaoImpl;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
@Repository("SongPredicateDao")
public class SongPredicateDaoImpl extends GenericDaoImpl<SongPredicate, PairKey<Song, Cluster>> implements SongPredicateDao {
}

