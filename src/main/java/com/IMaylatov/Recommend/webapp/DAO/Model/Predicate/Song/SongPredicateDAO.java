package com.IMaylatov.recommend.webapp.dao.model.predicate.song;

import com.IMaylatov.recommend.webapp.dao.generic.GenericDao;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.predicate.SongPredicate;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public interface SongPredicateDao extends GenericDao<SongPredicate, PairKey<Song, Cluster>> {
}
