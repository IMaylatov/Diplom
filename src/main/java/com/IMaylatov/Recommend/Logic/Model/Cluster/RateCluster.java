package com.IMaylatov.Recommend.Logic.Model.Cluster;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Song;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность - оценка центроида
 */
@Entity
@Table(name = "RateCluster")
public class RateCluster {
    //region Private field
    @EmbeddedId
    private PairKey id;

    @Column(name="Value")
    private int value;
    //endregion

    //region Contructor
    private RateCluster(){
    }

    public RateCluster(PairKey id, int value) {
        this.id = id;
        this.value = value;
    }
    //endregion

    //region Getter Setter
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PairKey getId(){
        return id;
    }

    public Cluster getCluster(){
        return id.getCluster();
    }

    public Song getSong(){
        return id.getSong();
    }
    //endregion


    @Override
    public String toString() {
        return "RateCluster{" +
                "ClusterId=" + id.getCluster().getId() +
                ", SongId=" + id.getSong().getId() +
                ", value=" + value +
                '}';
    }

    //region Primary key
    @Embeddable
    public static class PairKey implements Serializable{
        //region Private field
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "ClusterID", nullable = false)
        private Cluster cluster;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "SongID", nullable = false)
        private Song song;
        //endregion

        //region Constructor
        private PairKey(){
        }

        public PairKey(Cluster cluster, Song song) {
            this.cluster = cluster;
            this.song = song;
        }
        //endregion

        //region Getter Setter
        public Cluster getCluster() {
            return cluster;
        }

        public Song getSong() {
            return song;
        }
        //endregion
    }
    //endregion
}
