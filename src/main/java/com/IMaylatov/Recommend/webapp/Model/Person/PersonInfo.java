package com.IMaylatov.Recommend.webapp.Model.Person;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "Password")
    private String password;

    @Column(name = "Enabled")
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "PersonInfoID", insertable = false, updatable = false)
    private List<PersonRoles> personRoles = new ArrayList<>();

    private PersonInfo(){}

    public PersonInfo(Person person) {
        this.person = person;
    }

    public PersonInfo(Person person, String name) {
        this.person = person;
        this.name = name;
    }

    public PersonInfo(String name, String password, boolean enabled) {
        this.name = name;
        this.password = password;
        this.enabled = enabled;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<PersonRoles> getPersonRoles() {
        return personRoles;
    }

    public void setPersonRoles(List<PersonRoles> personRoles) {
        this.personRoles = personRoles;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                '}';
    }
}
