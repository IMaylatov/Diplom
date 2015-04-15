package com.IMaylatov.Recommend.Logic.Model;

import com.IMaylatov.Recommend.Logic.Model.Predicate.SongPredicate;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Сущность "Трек"
 */
@Entity
@Table(name="Song")
public class Song implements Serializable {
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="song_id_seq")
    @SequenceGenerator(name = "song_id_seq", sequenceName = "song_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SongID", updatable = false)
    private List<SongPredicate> predicates = new ArrayList<>();
    //endregion

    //region Constructor
    public Song() {
    }
    //endregion

    //region Getter Setter
    public long getId() {
        return id;
    }

    public boolean addPredicate(float value, Cluster cluster){
        SongPredicate predicate = getPredicate(cluster);
        if (predicate == null){
            return predicates.add(new SongPredicate(new PairKey<>(this, cluster), value));
        }else
            predicate.setValue(value);
        return false;
    }

    public SongPredicate getPredicate(Cluster cluster){
        for (SongPredicate songPredicate : predicates)
            if (songPredicate.getSong().getId() == id && songPredicate.getCluster().getId() == cluster.getId())
                return songPredicate;
        return null;
    }

    public boolean removePredicate(Cluster cluster){
        SongPredicate predicate = getPredicate(cluster);
        return predicates.remove(predicate);
    }

    public Iterator<SongPredicate> iteratorPredicate(){
        return predicates.iterator();
    }
    //endregion

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        return id == song.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
