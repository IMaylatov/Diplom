package com.IMaylatov.recommend.webapp.model;

import com.IMaylatov.recommend.webapp.model.predicate.PersonPredicate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="Person")
public class Person{
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="person_id_seq")
    @SequenceGenerator(name = "person_id_seq", sequenceName = "person_id_seq", allocationSize = 1)
    @Column(name="id")
    private long id;

    @ElementCollection
    @CollectionTable(name = "RatePerson", joinColumns = @JoinColumn(name = "PersonId"))
    @Column(name = "value")
    @MapKeyJoinColumn(name = "SongId")
    private Map<Song, Long> rates = new HashMap<>();

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
    public void setId(long id) {
        this.id = id;
    }

    public Map<Song, Long> getRates() {
        return rates;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    public Cluster getCluster() {
        return cluster;
    }

    public void setPredicate(float value) {
        predicate.setValue(value);
    }
    public float getPredicate() {
        return predicate.getValue();
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
