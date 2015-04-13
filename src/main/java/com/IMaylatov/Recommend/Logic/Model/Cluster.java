package com.IMaylatov.Recommend.Logic.Model;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RateCluster;
import com.IMaylatov.Recommend.Logic.Model.Rate.HasRates;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "Cluster")
public class Cluster implements HasRates<RateCluster>{
    //region Private field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cluster_id_seq")
    @SequenceGenerator(name = "cluster_id_seq", sequenceName = "cluster_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ClusterID", updatable = false)
    private List<RateCluster> listRates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ClusterID")
    private List<Person> persons = new ArrayList<>();
    //endregion

    //region Constructor
    public Cluster(){
    }
    //endregion

    //region Getter Setter
    public long getId(){
        return id;
    }

    public boolean addRate(Song song, int value){
        RateCluster rate = getRate(song);
        if (rate == null)
            return listRates.add(new RateCluster(new PairKey<>(this, song), value));
        else
            rate.setValue(value);
        return false;
    }

    public RateCluster getRate(Song song){
        for (RateCluster rate : listRates)
            if (rate.getSong().getId() == song.getId())
                return rate;
        return null;
    }

    public boolean removeRate(Song song){
        RateCluster rate = getRate(song);
        return listRates.remove(rate);
    }

    public Iterator<RateCluster> iteratorRates(){
        return listRates.iterator();
    }

    public Person addPerson(Person person){
        Person p = getPerson(person.getId());
        if (p == null) {
            persons.add(person);
            return person;
        }
        return p;
    }

    public Person getPerson(long id){
        for (Person person : persons)
            if (person.getId() == id)
                return person;
        return null;
    }

    public void removePerson(Person person){
        persons.remove(person);
    }

    public Iterator<Person> iteratorPerson(){
        return persons.iterator();
    }
    //endregion

    @Override
    public String toString() {
        return "Cluster{" +
                "id=" + id +
                '}';
    }
}
