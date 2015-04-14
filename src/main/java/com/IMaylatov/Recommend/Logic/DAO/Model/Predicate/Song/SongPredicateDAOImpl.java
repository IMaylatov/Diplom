package com.IMaylatov.Recommend.Logic.DAO.Model.Predicate.Song;

import com.IMaylatov.Recommend.Logic.DAO.Generic.GenericDAOImpl;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Song;
import org.springframework.stereotype.Repository;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Repository("SongPredicateDAO")
public class SongPredicateDAOImpl extends GenericDAOImpl<SongPredicate, PairKey<Song, Cluster>> implements SongPredicateDAO{
}
