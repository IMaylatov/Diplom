package com.IMaylatov.recommend.webapp.model.rate.concreteRate;

import com.IMaylatov.recommend.webapp.model.Person;
import com.IMaylatov.recommend.webapp.model.Song;
import com.IMaylatov.recommend.webapp.model.rate.RateValue;

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
                "id=" + id +
                " ,PersonId=" + id.getK1().getId() +
                " , SongId=" + id.getK2().getId() +
                '}';
    }
}
