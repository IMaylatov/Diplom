package com.IMaylatov.Recommend.Logic.Model.Rate;

import javax.persistence.Column;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 12.04.2015.
 */
public class Rate implements Rateable {
    @Column(name="Value")
    protected int value;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}
