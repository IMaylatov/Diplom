package com.IMaylatov.Recommend.Logic.SVD.DealerRate;

import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public class DealerRateImpl implements DealerRate{
    @Override
    public int getRateInt(Person person, Song song) {
        float average = person.getCluster() != null ?
                (float) person.getCluster().getSummaRate() / (float) person.getCluster().getCountRate() : 0;
        if(!song.getPredicates().containsKey(person.getCluster()))
            return Math.round(average + person.getPredicate());
        else
            return Math.round(average + person.getPredicate() + song.getPredicates().get(person.getCluster()));
    }

    @Override
    public float getRateFloat(Person person, Song song) {
        float average = person.getCluster() != null ?
                (float) person.getCluster().getSummaRate() / (float) person.getCluster().getCountRate() : 0;
        if(!song.getPredicates().containsKey(person.getCluster()))
            return average + person.getPredicate();
        else
            return average + person.getPredicate() + song.getPredicates().get(person.getCluster());
    }
}
