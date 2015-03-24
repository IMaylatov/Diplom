package com.IMaylatov.Recommend;

import com.IMaylatov.Recommend.DAO.Generic.GenericDAO;
import com.IMaylatov.Recommend.Service.Generic.GenericService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Liggoriya on 21.03.2015.
 */

public enum  DbContext {
    INSTANCE;

    private final ClassPathXmlApplicationContext instance =
            (new ClassPathXmlApplicationContext(new String[] {"app-context.xml"},true));

    public Object getBean(String name){
        return instance.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends GenericDAO> T getDAO(String name){
        return (T) instance.getBean(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends GenericService> T getService(String name){
        return (T) instance.getBean(name);
    }
}


