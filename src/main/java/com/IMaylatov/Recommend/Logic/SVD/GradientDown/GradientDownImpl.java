package com.IMaylatov.Recommend.Logic.SVD.GradientDown;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import java.util.List;
import java.util.Map.Entry;

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

        for(Person person : cluster.getPersons()){
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                Song song = rate.getKey();
                float e = rate.getValue() -
                        (average + person.getPredicate() + rate.getKey().getPredicates().get(cluster));

                person.setPredicate((float) (person.getPredicate() +
                                    learningRates * (e -L4 * person.getPredicate())));
                song.getPredicates().put(cluster,
                        (float) (song.getPredicates().get(cluster) +
                                learningRates * (e - L4 * song.getPredicates().get(cluster))));
            }
        }
    }

    @Override
    public void down(List<Cluster> clusters) {
        for(Cluster cluster : clusters)
            down(cluster);
    }
}
