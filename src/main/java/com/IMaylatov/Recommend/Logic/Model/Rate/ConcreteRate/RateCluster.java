package com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.Rate;
import com.IMaylatov.Recommend.Logic.Model.Rate.RateImpl;
import com.IMaylatov.Recommend.Logic.Model.Song;

import javax.persistence.*;

@Entity
@Table(name = "RateCluster")
public class RateCluster extends RateImpl implements Rate<Cluster, Song> {
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

    public RateCluster(PairKey<Cluster, Song> id, int value) {
        this.id = id;
        this.value = value;
    }
    //endregion

    //region Getter Setter
    public PairKey<Cluster, Song> getId(){
        return id;
    }

    public Cluster getCluster(){
        return id.getK1();
    }
    public Cluster getAppraiser(){return getCluster();}

    public Song getSong(){
        return id.getK2();
    }
    //endregion

    @Override
    public String toString() {
        return "RateCluster{" +
                "ClusterId=" + id.getK1().getId() +
                ", SongId=" + id.getK2().getId() +
                ", value=" + value +
                '}';
    }
}
