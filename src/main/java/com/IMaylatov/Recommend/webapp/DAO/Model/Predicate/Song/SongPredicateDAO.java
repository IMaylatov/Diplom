package com.IMaylatov.recommend.webapp.dao.Model.Predicate.Song;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAO;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.predicate.SongPredicate;
import com.IMaylatov.recommend.webapp.model.Rate.PairKey.PairKey;
import com.IMaylatov.recommend.webapp.model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public interface SongPredicateDAO extends GenericDAO<SongPredicate, PairKey<Song, Cluster>> {
}
