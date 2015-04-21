package com.IMaylatov.Recommend.Main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.IMaylatov.Recommend.Logic.LoaderData.*;

import java.util.HashMap;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015.
 */
public class LoaderMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        LoaderData loaderData = (LoaderData) context.getBean("LoaderData");
        HashMap<String, String> files = new HashMap<>();
        files.put("person", "data/person.dat");
        files.put("song", "data/song.dat");
        files.put("rate", "data/ratings.dat");
        loaderData.loadAll(files);
    }
}
