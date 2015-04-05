package com.IMaylatov.Recommend.Logic.Model;

import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
            rate = new RatePerson(new RatePerson.PairKey(this, song), value);
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

    public List<RatePerson> getRates(){
        List<RatePerson> cloneRateList = new ArrayList<>(rates);
        return cloneRateList;
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
