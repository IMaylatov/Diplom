package com.IMaylatov.Recommend.Logic.Controller;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 20.04.2015
 */
@Controller
public class SVDController {
//    @Autowired
//    private SVD svd;
//
//    @RequestMapping("/calculatePredicate")
//    @ResponseBody public String calculatePredicate(){
//        svd.calculatePredicate();
//        return "Calculate predicate was successful completed.";
//    }

    @Autowired
    private PersonDAO personDAO;

    @RequestMapping("/test")
    public String test(){
        return "view";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody
    Person getUser(){
        return personDAO.list().get(0);
    }
}
