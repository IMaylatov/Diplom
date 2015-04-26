package com.IMaylatov.Recommend.webapp.Model.Person;

import javax.persistence.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 26.04.2015.
 */
@Entity
@Table(name = "PersonInfo")
public class PersonInfo {
    @Id
    @Column(name = "PersonId")
    private Long personId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Person person;

    @Column(name = "Name")
    private String name;

    private PersonInfo(){}

    public PersonInfo(Person person) {
        this.person = person;
    }

    public PersonInfo(Person person, String name) {
        this.person = person;
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}
