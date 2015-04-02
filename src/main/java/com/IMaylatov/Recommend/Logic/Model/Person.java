package com.IMaylatov.Recommend.Logic.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность "Персона"
 */
@Entity
@Table(name="Person")
public class Person {
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PersonId")
    private List<Rate> rateList;
    //endregion

    //region Constructor
    public Person() {
        rateList = new ArrayList<>();
    }
    //endregion

    //region getter setter
    public long getId() {
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    /**
     * Пользователь ставит оценку
     * @param song песня
     * @param value оценка
     * @return оценка
     */
    public Rate addRate(Song song, int value){
        Rate rate = new Rate(new Rate.RatePK(this, song), value);
        rateList.add(rate);
        return rate;
    }

    /**
     * Получить оценку пользователя для песни
     * @param song песня для которой нужно найти оценку
     * @return найденная оценка
     */
    public Rate getRate(Song song){
        for (Rate rate : rateList)
            if (rate.getSong().equals(song))
                return rate;
        return null;
    }
    //endregion

    //region public method
    @Override
    public String toString(){
        return "Person: id = " + id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Person person = (Person) object;

        if (id != person.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
    //endregion
}
