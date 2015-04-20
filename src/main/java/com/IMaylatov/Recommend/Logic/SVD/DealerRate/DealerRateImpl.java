package com.IMaylatov.Recommend.Business.SVD.DealerRate;

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public class DealerRateImpl implements DealerRate{
    @Override
    public int getRate(Person person, Song song) {
        float average = (float) person.getCluster().getSummaRate() / (float) person.getCluster().getCountRate();
        if(song.getPredicate(person.getCluster()) == null)
            return Math.round(average + person.getPredicate());
        else
            return Math.round(average + person.getPredicate() + song.getPredicate(person.getCluster()).getValue());
    }
}
