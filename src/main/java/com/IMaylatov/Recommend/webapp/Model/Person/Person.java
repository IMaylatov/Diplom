package com.IMaylatov.Recommend.webapp.Model.Person;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Predicate.PersonPredicate;
import com.IMaylatov.Recommend.webapp.Model.Rate.Ratesable;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="Person")
public class Person implements Ratesable{
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @ElementCollection
    @CollectionTable(name = "ratePerson", joinColumns = @JoinColumn(name = "PersonId"))
    @Column(name = "value")
    @MapKeyJoinColumn(name = "SongId")
    private Map<Song, Integer> rates = new HashMap<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClusterId")
    private Cluster cluster;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
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
    public void setId(long id) {
        this.id = id;
    }

    public Map<Song, Integer> getRates() {
        return rates;

    }
    public void setRates(Map<Song, Integer> rates) {
        this.rates = rates;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    public Cluster getCluster() {
        return cluster;
    }

    public void setPredicate(float value) {
        if(predicate == null)
            predicate = new PersonPredicate(this);
        predicate.setValue(value);
    }
    public float getPredicate() {
        if(predicate == null)
            predicate = new PersonPredicate(this);
        return predicate.getValue();
    }
    //endregion

    @Override
    public String toString() {
        return "personId{" +
                "id=" + id +
                ", cluster_Id=" + (cluster != null ? cluster.getId() : "") +
                '}';
    }
}
