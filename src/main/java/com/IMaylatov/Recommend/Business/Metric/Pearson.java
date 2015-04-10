package com.IMaylatov.Recommend.Business.Metric;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 04.04.2015.
 */

import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Rate.RatePerson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ���������� �������
 */
public class Pearson implements Metric{
    @Override
    public double compare(Pearson person1, Pearson person2) throws IllegalArgumentException {
        double X = 0, Y = 0;

//        List<Pair> commonRate = new ArrayList<>();
//        Iterator<RatePerson> ratePersonIterator = person1.getRateIterator();
//        while(ratePersonIterator.hasNext()){
//            RatePerson ratePerson1 = ratePersonIterator.next();
//            RatePerson ratePerson2 = person2.getRate(ratePerson1.getK2());
//            if (ratePerson2 != null) {
//                commonRate.add(new Pair(ratePerson1, ratePerson2));
//                X += ratePerson1.getValue();
//                Y += ratePerson2.getValue();
//            }
//        }
//        if (commonRate.size() < 3)
//            throw new IllegalArgumentException("���������� ������������ ����� ������ ���� �� ������ 3");
//        X /= commonRate.size();
//        Y /= commonRate.size();

        //���������, ����������� ��� X � Y, ������������� ����������(���������� ��� ��������� � �����������)
        double numerator = 0, denominatorX = 0, denominatorY = 0, gapX, gapY;
//        for (Pair pair : commonRate){
//            gapX = pair.person1Rate.getValue() - X;
//            gapY = pair.person2Rate.getValue() - Y;
//            numerator += gapX * gapY;
//            denominatorX += gapX * gapX;
//            denominatorY += gapY * gapY;
//        }
        if((denominatorX*denominatorY <= 0) || (numerator < 0))
            throw new IllegalArgumentException("����������� �������������� �����������");

        return numerator / (Math.sqrt(denominatorX*denominatorY));
    }

    class Pair{
        public RatePerson person1Rate;
        public RatePerson person2Rate;
        public Pair(RatePerson person1Rate, RatePerson person2Rate) {
            this.person1Rate = person1Rate;
            this.person2Rate = person2Rate;
        }
    }
}
