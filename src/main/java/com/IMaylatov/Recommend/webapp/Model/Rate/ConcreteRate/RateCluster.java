package com.IMaylatov.recommend.webapp.model.rate.concreteRate;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.recommend.webapp.model.Cluster;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.RateValue;

import javax.persistence.*;

@Entity
@Table(name = "rateCluster")
public class RateCluster extends RateValue {
    //region Private field
    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name="k1", joinColumns = @JoinColumn(name="ClusterID")),
            @AssociationOverride(name="k2", joinColumns = @JoinColumn(name="SongID"))
    })
    private PairKey<Cluster, Song> id;
    //endregion

    //region Contructor
    private RateCluster(){
    }
    //endregion

    //region Getter Setter
    public PairKey<Cluster, Song> getId(){
        return id;
    }

    public Cluster getCluster(){
        return id.getK1();
    }

    public Song getSong(){
        return id.getK2();
    }
    //endregion

    @Override
    public String toString() {
        return "rateCluster{" +
                "ClusterId=" + id.getK1().getId() +
                ", SongId=" + id.getK2().getId() +
                ", value=" + value +
                '}';
    }
}
