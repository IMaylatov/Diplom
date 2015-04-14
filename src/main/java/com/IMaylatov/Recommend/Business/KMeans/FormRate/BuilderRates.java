package com.IMaylatov.Recommend.Business.KMeans.FormRate;

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;

import java.util.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class BuilderRates implements BuilderRatesable {
    @Override
    public void form(Cluster cluster) {
        Set<Long> songsIdSet = new HashSet<>();

        Iterator<Person> personIterator = cluster.iteratorPerson();
        while(personIterator.hasNext()){
            Person person = personIterator.next();

            Iterator<RatePerson> ratePersonIterator = person.iteratorRates();
            while(ratePersonIterator.hasNext()){
                RatePerson ratePerson = ratePersonIterator.next();
                if (!songsIdSet.contains(ratePerson.getSong().getId())){
                    songsIdSet.add(ratePerson.getSong().getId());
                    cluster.addRate(ratePerson.getSong(), ratePerson.getValue());
                }
            }
        }
    }
}