package com.IMaylatov.Recommend.Business.KMeans.SpreadPeople;

import com.IMaylatov.Recommend.Logic.Model.Cluster.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 07.04.2015.
 */
public class SpreadPersonToClusterImpl implements SpreadPersonToCluster{
    @Override
    public List<Cluster> simpleSpread(List<Person> persons, int k) {
        if (persons == null)
            throw new IllegalArgumentException("Persons = null");
        if ((k <= 0) && (persons.size() >= k))
            throw new IllegalArgumentException(
                    String.format("Неверно заданы параметры {k=%d; person count = %d}", k, persons.size()));

        List<Cluster> clusters = new ArrayList<>();
        for(int i = 0; i < k; i++)
            clusters.add(new Cluster());

        int indexCurrentCluster = 0;
        for(Person person : persons){
            clusters.get(indexCurrentCluster % k).addPerson(person);
            indexCurrentCluster++;
        }

        return clusters;
    }
}
