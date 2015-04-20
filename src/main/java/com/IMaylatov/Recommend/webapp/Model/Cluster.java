package com.IMaylatov.recommend.webapp.model;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Cluster")
public class Cluster{
    //region Private field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cluster_id_seq")
    @SequenceGenerator(name = "cluster_id_seq", sequenceName = "cluster_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @ElementCollection
    @CollectionTable(name = "RateCluster", joinColumns = @JoinColumn(name = "ClusterId"))
    @Column(name = "value")
    @MapKeyJoinColumn(name = "SongId")
    private Map<Song, Long> rates = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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

    public List<Person> getPersons() {
        return persons;
    }

    public Map<Song, Long> getRates() {
        return rates;
    }
    //endregion

    @Override
    public String toString() {
        return "Cluster{" +
                "id=" + id +
                ", countRate=" + countRate +
                ", summaRate=" + summaRate +
                '}';
    }
}
