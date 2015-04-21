package com.IMaylatov.recommend.webapp.dao.model.predicate.song;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDaoImpl;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.predicate.SongPredicate;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("SongPredicateDao")
public class SongPredicateDaoImpl extends GenericDaoImpl<SongPredicate, PairKey<Song, Cluster>> implements SongPredicateDao{
}
