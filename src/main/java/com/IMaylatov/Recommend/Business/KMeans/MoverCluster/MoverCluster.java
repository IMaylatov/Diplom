package com.IMaylatov.Recommend.Business.KMeans.MoverCluster;

import com.IMaylatov.Recommend.Logic.Model.Cluster;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.ConcreteRate.RatePerson;
import com.IMaylatov.Recommend.Logic.Model.Song;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 13.04.2015
 */
public class MoverCluster implements MoverClusterable {

    /**
     * Двигаем центроид. Для этого пройдемся по каждому пользователю из кластера.
     * Для каждого пользователя пройдемся по оценкам. Посчитам кол-во оценок и сумму оценок для каждой песни.
     * Разделим сумму оценок на их количество и получим новую оценку кластера для песни, т.е. новую координаты центра
     */
    @Override
    public boolean move(Cluster cluster) {
        boolean isMove = false;

        // <SongID, <Count rate for song, Summa rate for song>>
        Map<Song, InfoRate> songsRates = new HashMap<>();

        Iterator<Person> personIterator = cluster.iteratorPerson();
        while(personIterator.hasNext()){
            Person person = personIterator.next();

            Iterator<RatePerson> ratePersonIterator = person.iteratorRates();
            while(ratePersonIterator.hasNext()){
                RatePerson ratePerson = ratePersonIterator.next();

                InfoRate infoRate = songsRates.get(ratePerson.getSong());
                if (infoRate == null)
                    songsRates.put(ratePerson.getSong(), new InfoRate(1, ratePerson.getValue()));
                else {
                    infoRate.count++;
                    infoRate.summa += ratePerson.getValue();
                }
            }
        }

        for(Entry<Song, InfoRate> songRate : songsRates.entrySet()){
            int newValue = songRate.getValue().summa / songRate.getValue().count;
            if (cluster.getRate(songRate.getKey()) != null) {
                if (cluster.getRate(songRate.getKey()).getValue() != newValue) {
                    isMove = true;
                    cluster.getRate(songRate.getKey()).setValue(newValue);
                }
            }else
                cluster.addRate(songRate.getKey(), newValue);
        }

        return isMove;
    }

    private class InfoRate {
        public int count;
        public int summa;

        public InfoRate(int count, int summa) {
            this.count = count;
            this.summa = summa;
        }

        @Override
        public String toString() {
            return "InfoRate{" +
                    "count=" + count +
                    ", summa=" + summa +
                    '}';
        }
    }
}
