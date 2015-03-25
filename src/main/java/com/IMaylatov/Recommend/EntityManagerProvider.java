package com.IMaylatov.Recommend;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by Liggoriya on 25.03.2015.
 */
public class EntityManagerProvider {
    public static EntityManagerFactory emf;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public static EntityManager createEntityManager(){
        return emf.createEntityManager();
    }
}
