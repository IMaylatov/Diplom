package com.IMaylatov.Recommend.Logic.Model.Rate.PairKey;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 09.04.2015.
 */
@Embeddable
public class PairKey<K1, K2> implements PairKeyable<K1, K2>, Serializable{
    @ManyToOne(fetch = FetchType.EAGER)
    protected K1 k1;
    @ManyToOne(fetch = FetchType.EAGER)
    protected K2 k2;

    @Override
    public K1 getK1() {
        return k1;
    }

    @Override
    public K2 getK2() {
        return k2;
    }

    private PairKey(){}
    public PairKey(K1 k1, K2 k2) {
        this.k1 = k1;
        this.k2 = k2;
    }
}
