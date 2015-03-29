package com.IMaylatov.Recommend.Model;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Сущность "Персона"
 */
@Entity
@Table(name="Rate")
public class Rate{
    //region field
    @EmbeddedId
    private RatePK id;

    @Column(name="Value")
    private int value;
    //endregion

    //region Constructor
    private Rate() {
    }

    public Rate(RatePK id, int value) {
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

    public RatePK getId() {
        return id;
    }
    public void setId(RatePK id) {
        this.id = id;
    }

    public Person getPerson() {
        return id.getPerson();
    }
    public void setPerson(Person person) {
        id.setPerson(person);
    }

    public Song getSong() {
        return id.getSong();
    }
    public void setSong(Song song) {
        id.setSong(song);
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

    //region Primary Key
    @Embeddable
    public static class RatePK implements Serializable {
        //region field
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PersonId", nullable = false)
        private Person person;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "SongId", nullable = false)
        private Song song;
        //endregion

        //region Constructor
        private RatePK(){
        }

        public RatePK(Person person, Song song) {
            setPerson(person);
            setSong(song);
        }
        //endregion

        //region getter setter
        public Person getPerson() {
            return person;
        }
        public void setPerson(Person person) {
            if (person == null)
                throw new NullPointerException("Пользователь не может быть null");
            this.person = person;
        }

        public Song getSong() {
            return song;
        }
        public void setSong(Song song) {
            if (song == null)
                throw new NullPointerException("Песня не может быть null");
            this.song = song;
        }
        //endregion

        //region public method
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;

            RatePK ratePK = (RatePK) object;

            if (person != null ? !person.equals(ratePK.person) : ratePK.person != null) return false;
            if (song != null ? !song.equals(ratePK.song) : ratePK.song != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = person != null ? person.hashCode() : 0;
            result = 31 * result + (song != null ? song.hashCode() : 0);
            return result;
        }
        //endregion
    }
    //endregion
}
