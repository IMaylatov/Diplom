package com.IMaylatov.Recommend.Logic.Model.Rate.PairKey;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 09.04.2015.
 */

/**
 * Интерфекс двойного ключа
 */
public interface PairKeyable<K1, K2> {
    K1 getK1();
    K2 getK2();
}
