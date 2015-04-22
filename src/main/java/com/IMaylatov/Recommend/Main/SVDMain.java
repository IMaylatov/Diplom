package com.IMaylatov.Recommend.Main;

import com.IMaylatov.Recommend.Logic.SVD.SVD;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015.
 */
public class SVDMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context.xml");
        SVD svd = (SVD) context.getBean("SVD");
        svd.calculatePredicate();
    }
}
