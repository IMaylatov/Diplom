package com.IMaylatov.Recommend.Business.SVD.GradientDown;

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;

import java.util.Iterator;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
public class GradientDownImpl implements GradientDown{
    //Параметр регулиризации
    private final double L4 = 0.02;
    //Параметр обучаемости
    private final double learningRates = 0.005;

    @Override
    public void down(Cluster cluster) {
        float average = (float)cluster.getSummaRate() / (float)cluster.getCountRate();

        Iterator<Person> personIterator = cluster.iteratorPerson();
        while(personIterator.hasNext()){
            Person person = personIterator.next();
            Iterator<RatePerson> ratePersonIterator = person.iteratorRates();
            while(ratePersonIterator.hasNext()){
                RatePerson ratePerson = ratePersonIterator.next();

                Song song = ratePerson.getSong();
                float e = ratePerson.getValue() - (average + person.getPredicate() + song.getPredicate(cluster).getValue());

                person.setPredicate((float) (person.getPredicate() +
                        learningRates * (e - L4 * person.getPredicate())));

                song.setPredicate((float) (song.getPredicate(cluster).getValue() +
                        learningRates * (e - L4 * song.getPredicate(cluster).getValue())),
                        cluster);
            }
        }
    }
}
