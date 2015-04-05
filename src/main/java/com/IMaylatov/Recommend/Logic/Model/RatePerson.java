package com.IMaylatov.Recommend.Logic.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Сущность "Персона"
 */
@Entity
@Table(name="RatePerson")
public class RatePerson{
    //region field
    @EmbeddedId
    private PairKey id;

    @Column(name="Value")
    private int value;
    //endregion

    //region Constructor
    private RatePerson() {
    }

    public RatePerson(PairKey id, int value) {
        this.id = id;
        this.value = value;
    }
    //endregion

    //region getter setter
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public PairKey getId() {
        return id;
    }


    public Song getSong() {
        return id.getSong();
    }

    public Person getPerson() {
        return id.getPerson();
    }
    //endregion

    //region public method
    @Override
    public String toString(){
        return String.format("Rate: PersonId = %d; SongId = %d; Value = %d",
                id.getPerson().getId(),
                id.getSong().getId(),
                value);
    }
    //endregion

    //region Primary key
    @Embeddable
    public static class PairKey implements Serializable {
        //region Private field
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PersonId", nullable = false)
        private Person person;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "SongID", nullable = false)
        private Song song;
        //endregion

        //region Constructor
        private PairKey(){
        }

        public PairKey(Person person, Song song) {
            this.person = person;
            this.song = song;
        }
        //endregion

        //region Getter Setter
        public Person getPerson() {
            return person;
        }

        public Song getSong() {
            return song;
        }
        //endregion
    }
    //endregion
}
