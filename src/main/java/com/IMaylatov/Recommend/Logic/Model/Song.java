package com.IMaylatov.Recommend.Logic.Model;

import javax.persistence.*;
import java.io.Serializable;

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
    //endregion

    //region Constructor
    public Song() {
    }
    //endregion

    //region Getter Setter
    public long getId() {
        return id;
    }
    //endregion

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                '}';
    }
}
