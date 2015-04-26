package com.IMaylatov.Recommend.Logic.KMeans.BuilderRates;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import java.util.*;
import java.util.Map.Entry;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class BuilderRatesImpl implements BuilderRates {
    @Override
    public void build(Cluster cluster) {
        cluster.setCountRate(0);
        cluster.setSummaRate(0);
        Map<Song, Integer> rates = new HashMap<>();
        for(Person person : cluster.getPersons()){
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                cluster.setCountRate(cluster.getCountRate() + 1);
                cluster.setSummaRate(cluster.getSummaRate() + rate.getValue());
                rates.put(rate.getKey(), rate.getValue());
            }
        }
        cluster.setRates(rates);
    }
}
