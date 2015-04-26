package com.IMaylatov.Recommend.Logic.KMeans.MoverCluster;

import com.IMaylatov.Recommend.webapp.Model.Cluster;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 13.04.2015
 */
@Service("MoverCluster")
public class MoverClusterImpl implements MoverCluster {
    /**
     * Двигаем центроид. Для этого пройдемся по каждому пользователю из кластера.
     * Для каждого пользователя пройдемся по оценкам. Посчитам кол-во оценок и сумму оценок для каждой песни.
     * Разделим сумму оценок на их количество и получим новую оценку кластера для песни, т.е. новую координаты центра
     */
    @Override
    public boolean move(Cluster cluster) {
        Map<Song, RateInfo> ratesInfoForSong = new HashMap<>();
        for(Person person : cluster.getPersons()){
            for(Entry<Song, Integer> rate : person.getRates().entrySet()){
                Song song = rate.getKey();
                RateInfo rateInfo = ratesInfoForSong.get(song);
                if(rateInfo == null)
                    rateInfo = new RateInfo(1, rate.getValue());
                else {
                    rateInfo.countRate++;
                    rateInfo.summaRate += rate.getValue();
                }
                ratesInfoForSong.put(song, rateInfo);
            }
        }

        boolean isMove = false;
        Map<Song, Integer> rateForSong = new HashMap<>();
        for(Entry<Song, RateInfo> rate : ratesInfoForSong.entrySet()){
            Song song = rate.getKey();
            int newValue = Math.round((float)rate.getValue().summaRate / (float)rate.getValue().summaRate);
            if ((cluster.getRates().containsKey(song)) && (cluster.getRates().get(song) == newValue))
                isMove = true;
            rateForSong.put(song, newValue);
        }

        cluster.setRates(rateForSong);
        return isMove;
    }

    private class RateInfo{
        public int countRate;
        public int summaRate;

        public RateInfo(int countRate, int summaRate) {
            this.countRate = countRate;
            this.summaRate = summaRate;
        }
    }
}
