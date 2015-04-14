package com.IMaylatov.Recommend.Logic.Model.Predicate;

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Song;

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

    public SongPredicate(PairKey<Song, Cluster> id, float value){
        this.id = id;
        this.value = value;
    }

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
