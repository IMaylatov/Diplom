package com.IMaylatov.Recommend.webapp.Model;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.Ratesable;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cluster")
public class Cluster implements Ratesable {
    //region Private field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cluster_id_seq")
    @SequenceGenerator(name = "cluster_id_seq", sequenceName = "cluster_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "rateCluster", joinColumns = @JoinColumn(name = "ClusterId"))
    @Column(name = "value")
    @MapKeyJoinColumn(name = "SongId")
    private Map<Song, Integer> rates = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClusterID")
    private List<Person> persons = new ArrayList<>();

    @Column(name = "CountRate")
    private int countRate;

    @Column(name = "SummaRate")
    private int summaRate;
    //endregion

    //region Constructor
    public Cluster(){
    }
    //endregion

    //region Getter Setter
    public long getId(){
        return id;
    }

    public int getCountRate() {
        return countRate;
    }
    public void setCountRate(int countRate) {
        this.countRate = countRate;
    }

    public int getSummaRate() {
        return summaRate;
    }
    public void setSummaRate(int summaRate) {
        this.summaRate = summaRate;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
    public List<Person> getPersons() {
        return persons;
    }

    public void setRates(Map<Song, Integer> rates) {
        this.rates = rates;
    }
    public Map<Song, Integer> getRates() {
        return rates;
    }
    //endregion

    @Override
    public String toString() {
        return "cluster{" +
                "id=" + id +
                ", countRate=" + countRate +
                ", summaRate=" + summaRate +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Cluster cluster = (Cluster) object;

        return id == cluster.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
