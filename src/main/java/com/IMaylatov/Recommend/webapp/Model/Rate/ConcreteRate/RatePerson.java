package com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate;

import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Rate.RateValue;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import javax.persistence.*;

@Entity
@Table(name="ratePerson")
public class RatePerson extends RateValue {
    //region field
    @EmbeddedId
    @AssociationOverrides({
            @AssociationOverride(name="k1", joinColumns = @JoinColumn(name="PersonID")),
            @AssociationOverride(name="k2", joinColumns = @JoinColumn(name="SongID"))
    })
    private PairKey<Person, Song> id;
    //endregion

    //region Constructor
    private RatePerson() {
    }

    public RatePerson(PairKey<Person, Song> id) {
        this.id = id;
    }
    //endregion

    //region getter setter
    public PairKey getId() {
        return id;
    }

    public Person getPerson() {
        return id.getK1();
    }

    public Song getSong() {
        return id.getK2();
    }
    //endregion


    @Override
    public String toString() {
        return "ratePerson{" +
                "PersonId=" + id.getK1().getId() +
                " , SongId=" + id.getK2().getId() +
                " , Value =" + getValue() +
                '}';
    }
}
