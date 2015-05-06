package com.IMaylatov.Recommend.webapp.Model.Person;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 30.04.2015
 */
@Entity
@Table(name = "PersonRoles")
public class PersonRoles{
    @EmbeddedId
    private PairKey id;

    private PersonRoles(){}

    public PersonRoles(PairKey id) {
        this.id = id;
    }

    public PairKey getId() {
        return id;
    }

    public PersonInfo getPersonInfo(){
        return id.getPersonInfo();
    }

    public String getRole() {
        return id.getRole();
    }

    @Override
    public String toString() {
        return "PersonRoles{" +
                "PersonInfo.name=" + id.getPersonInfo().getName() +
                " ,Role=" + id.getRole() +
                '}';
    }

    @Embeddable
    public static class PairKey implements Serializable {
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PersonInfoID")
        private PersonInfo personInfo;

        @Column(name = "Role")
        private String role;

        private PairKey(){}

        public PairKey(PersonInfo personInfo, String role) {
            this.personInfo = personInfo;
            this.role = role;
        }

        public PersonInfo getPersonInfo() {
            return personInfo;
        }

        public String getRole() {
            return role;
        }
    }

}
