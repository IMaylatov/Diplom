package com.IMaylatov.Recommend.webapp.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="song")
public class Song implements Serializable {
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="song_id_seq")
    @SequenceGenerator(name = "song_id_seq", sequenceName = "song_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @ElementCollection
    @CollectionTable(name = "SongPredicate", joinColumns = @JoinColumn(name = "SongId"))
    @Column(name = "value")
    @MapKeyJoinColumn(name = "ClusterId")
    private Map<Cluster, Float> predicates = new HashMap<>();
    //endregion

    //region Constructor
    public Song() {
    }
    //endregion

    //region Getter Setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setPredicates(Map<Cluster, Float> predicates) {
        this.predicates = predicates;
    }
    public Map<Cluster, Float> getPredicates() {
        return predicates;
    }
//endregion

    @Override
    public String toString() {
        return "song{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Song song = (Song) object;

        return id == song.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
