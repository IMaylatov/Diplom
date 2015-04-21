package com.IMaylatov.Recommend.webapp.DAO.Model.Predicate.Song;

import com.IMaylatov.Recommend.webapp.DAO.Generic.GenericDao;
import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate.PairKey;
import com.IMaylatov.Recommend.webapp.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 21.04.2015
 */
public interface SongPredicateDao extends GenericDao<SongPredicate, PairKey<Song, Cluster>> {
}
