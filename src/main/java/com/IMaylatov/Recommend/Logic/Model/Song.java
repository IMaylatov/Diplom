package com.IMaylatov.Recommend.Logic.Model;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SongId")
    private List<Rate> rateList;
    //endregion

    //region Constructor
    public Song() {
        rateList = new ArrayList<>();
    }
    //endregion

    //region getter setter
    public long getId() {
        return id;
    }

    /**
     * Для песни выставить оценку
     * @param person пользователей
     * @param value оценка
     * @return поставленная оценка
     */
    public Rate addRate(Person person, int value){
        Rate rate = new Rate(new Rate.RatePK(person, this), value);
        rateList.add(rate);
        return rate;
    }

    /**
     * Получить оценку пользователя для песни
     * @param person пользователь который поставил оценку
     * @return найденная оценка
     */
    public Rate getRate(Person person){
        for (Rate rate : rateList)
            if (rate.getPerson().equals(person))
                return rate;
        return null;
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
