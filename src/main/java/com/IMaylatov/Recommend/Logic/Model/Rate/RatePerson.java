package com.IMaylatov.Recommend.Logic.Model.Rate;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.PairKey.PairKey;
import com.IMaylatov.Recommend.Logic.Model.Song;

import javax.persistence.*;

@Entity
@Table(name="RatePerson")
public class RatePerson extends Rate implements AppraiserSongable<Person, Song>{
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

    public RatePerson(PairKey<Person, Song> id, int value) {
        this.id = id;
        this.value = value;
    }
    //endregion

    //region getter setter
    public PairKey getId() {
        return id;
    }

    public Person getPerson() {
        return id.getK1();
    }
    public Person getAppraiser(){return getPerson();}

    public Song getSong() {
        return id.getK2();
    }
    //endregion

    //region public method
    @Override
    public String toString(){
        return String.format("RatePerson: PersonId = %d; SongId = %d; Value = %d",
                id.getK1().getId(),
                id.getK2().getId(),
                value);
    }
    //endregion
}
