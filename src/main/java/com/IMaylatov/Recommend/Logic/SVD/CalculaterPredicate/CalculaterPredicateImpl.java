package com.IMaylatov.Recommend.Logic.SVD.CalculaterPredicate;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person;
import com.IMaylatov.Recommend.webapp.Model.Song;

import java.util.*;
import java.util.Map.Entry;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public class CalculaterPredicateImpl implements CalculaterPredicate {
    private final int L2 = 25;
    private final int L3 = 10;
    @Override
    public void calculate(Cluster cluster) {
        if(cluster.getCountRate() <= 0)
            throw new IllegalArgumentException("Invalid count rate in cluster, count = " + cluster.getCountRate());
        float average = (float) cluster.getSummaRate() / (float) cluster.getCountRate();

        Map<Song, PredicateInfo> songRateInfo = new HashMap<>();
        Map<Person, PredicateInfo> personRateInfo = new HashMap<>();

        for(Person person : cluster.getPersons()){
            int summaRateForPerson = 0;
            int countRateForPerson = 0;
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                Song song = rate.getKey();
                PredicateInfo songInfo = songRateInfo.get(song);
                if(songInfo == null){
                    songInfo = new PredicateInfo(1, rate.getValue());
                }else{
                    songInfo.countRate++;
                    songInfo.summaRate += rate.getValue();
                }
                songRateInfo.put(song, songInfo);

                summaRateForPerson += rate.getValue();
                countRateForPerson++;
            }
            personRateInfo.put(person, new PredicateInfo(countRateForPerson, summaRateForPerson));
        }

        for(Entry<Song, PredicateInfo> songInfo : songRateInfo.entrySet()){
            Song song = songInfo.getKey();
            PredicateInfo info = songInfo.getValue();
            song.getPredicates().put(cluster, (info.summaRate - info.countRate * average) / (L2 + info.countRate));
        }

        for(Person person : cluster.getPersons()){
            float songPredicate = 0;
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                songPredicate += rate.getKey().getPredicates().get(cluster);
            }
            PredicateInfo infoPerson = personRateInfo.get(person);
            person.setPredicate((infoPerson.summaRate - infoPerson.countRate*average - songPredicate) / (L3 + infoPerson.countRate));
        }
    }

    @Override
    public void calculate(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            calculate(cluster);
    }

    private class PredicateInfo{
        public int countRate;
        public int summaRate;

        public PredicateInfo(int countRate, int summaRate) {
            this.countRate = countRate;
            this.summaRate = summaRate;
        }
    }
}
