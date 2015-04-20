package com.IMaylatov.Recommend.Business.SVD.GradientDown;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.DAO.Model.Song.SongDAO;
import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;
import com.sun.prism.paint.Gradient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 17.04.2015
 */
@Repository("GradientDown")
public class GradientDownImpl implements GradientDown{
    //Параметр регулиризации
    private final double L4 = 0.02;
    //Параметр обучаемости
    private final double learningRates = 0.005;

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SongDAO songDAO;

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

                personDAO.update(person);
                songDAO.update(song);
            }
        }
    }

    @Override
    public void down(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            down(cluster);
    }
}
