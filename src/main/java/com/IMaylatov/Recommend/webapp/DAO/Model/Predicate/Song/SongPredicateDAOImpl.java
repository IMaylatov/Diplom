package com.IMaylatov.recommend.webapp.dao.Model.Predicate.Song;

import com.IMaylatov.recommend.webapp.dao.Generic.GenericDAOImpl;
import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.predicate.SongPredicate;
import com.IMaylatov.recommend.webapp.model.Rate.PairKey.PairKey;
import com.IMaylatov.recommend.webapp.model.Song;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("SongPredicateDAO")
public class SongPredicateDAOImpl extends GenericDAOImpl<SongPredicate, PairKey<Song, Cluster>> implements SongPredicateDAO{
}
