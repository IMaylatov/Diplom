package com.IMaylatov.recommend.webapp.model.predicate;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
public class PredicateValue implements Serializable{
    @Column(name="Value")
    protected float value;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
