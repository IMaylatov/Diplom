package com.IMaylatov.Recommend.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Liggoriya on 21.03.2015.
 */

/**
 * Сущность "Персона"
 */
@Entity
@Table(name="Person")
public class Person {
    //region field
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "person_id_seq", strategy = "person_id_seq")
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
