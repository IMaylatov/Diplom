package com.IMaylatov.Recommend.Logic.Model;

import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.RatePerson;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Сущность "Персона"
 */
@Entity
@Table(name="Person")
public class Person implements Serializable{
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PersonId", updatable = false)
    private List<RatePerson> rates = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClusterId")
    private Cluster cluster;
    //endregion

    //region Constructor
    public Person() {
    }
    //endregion

    //region getter setter
    public long getId() {
        return id;
    }

    /**
     * Пользователь ставит оценку или обновляет её если она уже поставлена
     * @param song песня
     * @param value оценка
     * @return оценка
     */
    public RatePerson addRate(Song song, int value){
        RatePerson rate = getRate(song);
        if (rate == null) {
            rate = new RatePerson(new PairKey<>(this, song), value);
            rates.add(rate);
        }
        else
            rate.setValue(value);
        return rate;
    }

    /**
     * Получить оценку пользователя для песни
     * @param song песня для которой нужно найти оценку
     * @return найденная оценка
     */
    public RatePerson getRate(Song song){
        for (RatePerson rate : rates)
            if (rate.getSong().getId() == song.getId())
                return rate;
        return null;
    }

    public void removeRate(Song song){
        RatePerson rate = getRate(song);
        if (rate != null){
            rates.remove(rate);
        }
    }

    /**
     * Возращает оценку для песни
     * @throws IllegalArgumentException Если нет оценки для песни
     */
    public int getRateValue(Song song){
        RatePerson rate = getRate(song);
        if (rate != null)
            return rate.getValue();
        throw new IllegalArgumentException("Для песни songId = " + song.getId() + " нет оценки");
    }

    public Iterator<RatePerson> getRateIterator(){
        return rates.iterator();
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    //endregion

    //region public method
    @Override
    public String toString(){
        return "Person: id = " + id;
    }
    //endregion
}
