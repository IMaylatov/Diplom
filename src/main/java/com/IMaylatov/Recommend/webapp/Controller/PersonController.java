package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DAO.Model.Person.PersonDao;
import com.IMaylatov.Recommend.webapp.Model.Person.Person;
import com.IMaylatov.Recommend.webapp.Service.PersonService;
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
    private PersonDao personDao;

    @RequestMapping(value = "/prioritySong")
    public @ResponseBody
    Long getPrioritySong(@RequestParam("userId") long userId){
        Person person = personDao.find(userId);
        return personService.getPrioritySong(person).getId();
    }
}
