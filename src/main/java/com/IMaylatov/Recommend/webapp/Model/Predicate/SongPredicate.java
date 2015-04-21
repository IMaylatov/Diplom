package com.IMaylatov.recommend.webapp.model.predicate;

import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.concreteRate.PairKey;

import javax.persistence.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Entity
@Table(name="SongPredicate")
public class SongPredicate extends PredicateValue{
    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name="k1", joinColumns = @JoinColumn(name="SongID")),
            @AssociationOverride(name="k2", joinColumns = @JoinColumn(name="ClusterID"))
    })
    private PairKey<Song, Cluster> id;

    private SongPredicate(){}

    public PairKey<Song, Cluster> getId() {
        return id;
    }

    public Song getSong(){
        return id.getK1();
    }

    public Cluster getCluster(){
        return id.getK2();
    }

    @Override
    public String toString() {
        return "SongPredicate{" +
                " SongId=" + id.getK1().getId() +
                ", ClusterID=" + id.getK2().getId() +
                ", Value=" + value +
                '}';
    }
}
