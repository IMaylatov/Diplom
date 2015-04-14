package com.IMaylatov.Recommend.Logic.Model.Rate;

import javax.persistence.Column;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 12.04.2015.
 */
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
