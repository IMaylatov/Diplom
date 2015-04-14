package com.IMaylatov.Recommend.Logic.DAO.Model.Predicate.Song;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public interface SongPredicateDAO extends GenericDAO<SongPredicate, PairKey<Song, Cluster>> {
}
