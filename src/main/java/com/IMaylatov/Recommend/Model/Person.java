package com.IMaylatov.Recommend.Model;


/**
 * Created by Liggoriya on 21.03.2015.
 */

import javax.persistence.*;
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "PersonId")
    private List<Rate> rateList;
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

    public List<Rate> getRateList() {
        return rateList;
    }
    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
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
