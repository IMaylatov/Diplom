package com.IMaylatov.Recommend.webapp.Model.Song;

import javax.persistence.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Entity
@Table(name = "SongInfo")
public class SongInfo {
    @Id
    @Column(name = "SongId")
    private Long songId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Song song;

    @Column(name = "Name")
    private String name;

    public SongInfo(Song song, String name) {
        this.song = song;
        this.name = name;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SongInfo{" +
                "songId=" + songId +
                ", name='" + name + '\'' +
                '}';
    }
}
