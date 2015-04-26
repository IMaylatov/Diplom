package com.IMaylatov.Recommend.webapp.Model.Predicate;

import com.IMaylatov.Recommend.webapp.Model.Person;

import javax.persistence.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 15.04.2015.
 */
@Entity
@Table(name="PersonPredicate")
public class PersonPredicate extends PredicateValue{
    @Id
    @Column(name = "PersonID")
    private Long personId;

    @OneToOne(fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    private Person person;

    public Person getPerson() {
        return person;
    }

    public Long getId(){
        return person.getId();
    }

    private PersonPredicate(){}

    public PersonPredicate(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonPredicate{" +
                "personID=" + person.getId() +
                ", value=" + value +
                '}';
    }
}
