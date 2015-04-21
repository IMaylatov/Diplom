package com.IMaylatov.Recommend.webapp.Model.Rate.ConcreteRate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 09.04.2015.
 */
@Embeddable
public abstract class PairKey<K1, K2> implements Serializable{
    @ManyToOne(fetch = FetchType.EAGER)
    protected K1 k1;
    @ManyToOne(fetch = FetchType.EAGER)
    protected K2 k2;

    public K1 getK1() {
        return k1;
    }

    public K2 getK2() {
        return k2;
    }

    public PairKey(){}
    public PairKey(K1 k1, K2 k2) {
        this.k1 = k1;
        this.k2 = k2;
    }
}
