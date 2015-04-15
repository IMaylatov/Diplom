package com.IMaylatov.Recommend.Logic.Model;

import com.IMaylatov.Recommend.Logic.Model.Predicate.PersonPredicate;
import com.IMaylatov.Recommend.Logic.Model.Rate.HasRates;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name="Person")
public class Person implements HasRates<RatePerson>, Serializable{
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

    @OneToOne(mappedBy = "person")
    private PersonPredicate predicate = new PersonPredicate(this);
    //endregion

    //region Constructor
    public Person() {
    }
    //endregion

    //region getter setter
    public long getId() {
        return id;
    }

    public boolean addRate(Song song, int value){
        RatePerson rate = getRate(song);
        if (rate == null)
            return rates.add(new RatePerson(new PairKey<>(this, song), value));
        else
            rate.setValue(value);
        return false;
    }

    public RatePerson getRate(Song song){
        for (RatePerson rate : rates)
            if (rate.getSong().getId() == song.getId())
                return rate;
        return null;
    }

    public boolean removeRate(Song song){
        RatePerson rate = getRate(song);
        return rates.remove(rate);
    }

    public Iterator<RatePerson> iteratorRates(){
        return rates.iterator();
    }

    public Cluster getCluster() {
        return cluster;
    }
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public float getPredicate() {
        return predicate.getValue();
    }
    public void setPredicate(float value) {
        predicate.setValue(value);
    }

    //endregion

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", cluster_Id=" + (cluster != null ? cluster.getId() : "") +
                '}';
    }
}
