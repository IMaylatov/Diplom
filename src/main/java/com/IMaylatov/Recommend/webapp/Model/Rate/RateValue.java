package com.IMaylatov.recommend.webapp.model.rate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 12.04.2015.
 */
@MappedSuperclass
public class RateValue {
    @Column(name="Value")
    protected int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
