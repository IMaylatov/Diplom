package com.IMaylatov.Recommend.Logic.Controller;

import com.IMaylatov.Recommend.Logic.DAO.Model.Person.PersonDAO;
import com.IMaylatov.Recommend.Logic.Model.Person;
import com.IMaylatov.Recommend.Logic.Model.Song;
import com.IMaylatov.Recommend.Logic.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 20.04.2015
 */
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonDAO personDAO;

    @RequestMapping(value = "/prioritySong")
    public @ResponseBody
    Song getPrioritySong(@RequestParam("userId") long userId){
        Person person = personDAO.find(userId);
        return personService.getPrioritySong(person);
    }

    @RequestMapping("/test")
    public String test(){
        return "view";
    }
}
