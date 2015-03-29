package com.IMaylatov.Recommend.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность "Трек"
 */
@Entity
@Table(name="Song")
public class Song {
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="song_id_seq")
    @SequenceGenerator(name = "song_id_seq", sequenceName = "song_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "SongId")
    private List<Rate> rateList;
    //endregion

    //region Constructor
    public Song() {
    }
    //endregion

    //region getter setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public List<Rate> getRateList() {
        return rateList;
    }
    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }
    //endregion

    //region public method
    @Override
    public String toString(){
        return "Song: id = " + id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Song song = (Song) object;

        if (id != song.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
    //endregion
}
