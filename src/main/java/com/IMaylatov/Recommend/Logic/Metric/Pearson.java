package com.IMaylatov.Recommend.Logic.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */
import com.IMaylatov.Recommend.webapp.Model.Rate.Ratesable;
import com.IMaylatov.Recommend.webapp.Model.Song.Song;

import java.util.Map.Entry;

/**
 * Корреляция Пирсона
 */
public class Pearson implements Metric {
    @Override
    public double compare(Ratesable person1, Ratesable person2){
        double X = 0, Y = 0;
        int countCommonRate = 0;

        for(Entry<Song, Integer> rate1 : person1.getRates().entrySet())
            if (person2.getRates().containsKey(rate1.getKey())) {
                X += rate1.getValue();
                Y += person2.getRates().get(rate1.getKey());
                countCommonRate++;
            }

        if (countCommonRate == 0)
            return Double.MAX_VALUE;
        X /= countCommonRate;
        Y /= countCommonRate;

        //Числитель, знаменатель для X и Y, промежуточные переменные(необходимы для числителя и знаменателя)
        double numerator = 0, denominatorX = 0, denominatorY = 0, gapX, gapY;
        for(Entry<Song, Integer> rate1 : person1.getRates().entrySet())
            if (person2.getRates().containsKey(rate1.getKey())) {
                gapX = rate1.getValue() - X;
                gapY = person2.getRates().get(rate1.getKey()) - Y;
                numerator += gapX * gapY;
                denominatorX += gapX * gapX;
                denominatorY += gapY * gapY;
            }

        if((denominatorX*denominatorY <= 0) || (numerator < 0))
            return Double.MAX_VALUE;

        return numerator / (Math.sqrt(denominatorX*denominatorY));
    }
}
