package com.IMaylatov.Recommend.webapp.Model.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Entity
@Table(name = "PersonHistory")
public class PersonHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="personhistory_id_seq")
    @SequenceGenerator(name = "personhistory_id_seq", sequenceName = "personhistory_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @Column(name = "PersonId", nullable = false)
    private Long personId;

    @Column(name = "SongId", nullable = false)
    private Long songId;

    @Column(name = "Date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private PersonHistory(){}

    public PersonHistory(Long personId, Long songId, Date date) {
        this.personId = personId;
        this.songId = songId;
        this.date = date;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PersonHistory{" +
                "id=" + id +
                ", personId=" + personId +
                ", songId=" + songId +
                ", date=" + date +
                '}';
    }
}
