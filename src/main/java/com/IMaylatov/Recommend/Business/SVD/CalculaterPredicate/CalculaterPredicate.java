package com.IMaylatov.Recommend.Business.SVD.CalculaterPredicate;

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Rate.Rate;
import com.IMaylatov.Recommend.Logic.Model.Song;

import java.util.*;
import java.util.Map.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public class CalculaterPredicate implements CalculaterPredicateable {
    private final int L2 = 25;
    private final int L3 = 10;

    @Override
    public void calculate(Cluster cluster) {
        int count = 0;
        float average = 0;

        Map<Person, List<Rate>> personRate = new HashMap<>();
        Map<Song, List<Rate>> songRate = new HashMap<>();

        Iterator<Person> personIterator = cluster.iteratorPerson();
        while(personIterator.hasNext()){
            Person person = personIterator.next();
            Iterator<RatePerson> ratePersonIterator = person.iteratorRates();
            while(ratePersonIterator.hasNext()){
                RatePerson ratePerson = ratePersonIterator.next();

                if(personRate.containsKey(ratePerson.getPerson()))
                    personRate.get(ratePerson.getPerson()).add(ratePerson);
                else {
                    List<Rate> rates = new ArrayList<>();
                    rates.add(ratePerson);
                    personRate.put(ratePerson.getPerson(), rates);
                }

                if(songRate.containsKey(ratePerson.getSong()))
                    songRate.get(ratePerson.getSong()).add(ratePerson);
                else{
                    List<Rate> rates = new ArrayList<>();
                    rates.add(ratePerson);
                    songRate.put(ratePerson.getSong(), rates);
                }

                count++;
                average += ratePerson.getValue();
            }
        }
        cluster.setSummaRate((int)average);
        cluster.setCountRate(count);

        average /= count;

        for(Entry<Person, List<Rate>> personInfo : personRate.entrySet()){
            float summa = 0;
            for(Rate rate : personInfo.getValue()){
                summa += rate.getValue() - average;
            }
            summa /= (L2 + personInfo.getValue().size());
            personInfo.getKey().setPredicate(summa);
        }

        for(Entry<Song, List<Rate>> songInfo : songRate.entrySet()){
            float summa = 0;
            for(Rate rate : songInfo.getValue()){
                summa += rate.getValue() - average - ((Person)rate.getAppraiser()).getPredicate();
            }
            summa /= (L3 + songInfo.getValue().size());
            songInfo.getKey().setPredicate(summa, cluster);
        }
    }

    @Override
    public void calculate(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            calculate(cluster);
    }
}
