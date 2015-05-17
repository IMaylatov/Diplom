package com.IMaylatov.Recommend.webapp.Model.Song;

import com.IMaylatov.Recommend.webapp.Model.Person.Person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 17.05.2015.
 */
@Entity
@Table(name="BlackList")
public class BlackList {
    @EmbeddedId
    private PairKey id;

    private BlackList(){}

    public BlackList(PairKey id) {
        this.id = id;
    }

    public PairKey getId() {
        return id;
    }

    public Song getSong(){
        return id.getSong();
    }

    public Person getPerson(){
        return id.getPerson();
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "PersonId=" + id.getPerson().getId() +
                "', SongId=" + id.getSong().getId()+" }'";
    }

    public static class PairKey implements Serializable{
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "SongId")
        private Song song;
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PersonId")
        private Person person;

        public Song getSong() {
            return song;
        }

        public Person getPerson() {
            return person;
        }

        public PairKey(Person person, Song song) {
            this.song = song;
            this.person = person;
        }
    }
}
