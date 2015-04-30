package com.IMaylatov.Recommend.webapp.Controller;

import com.IMaylatov.Recommend.webapp.DbUtil.DbUtil;
import com.IMaylatov.Recommend.webapp.Service.Person.PersonService;
import com.IMaylatov.Recommend.webapp.Service.Register.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com)
 * date: 30.04.2015
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private PersonService personService;
    @Autowired
    private DbUtil dbUtil;

    @RequestMapping("/addUser")
    public @ResponseBody
    RegisterUser addUser(@RequestParam("name") String name, @RequestParam("password") String password){
        List<Object> countPersonWithName = dbUtil.retrieve(
                String.format("Select count(*) from personInfo where name like '%%%s%%'", name));
        if(Integer.parseInt(countPersonWithName.get(0).toString()) == 0){
            personService.savePerson(name, password);
            return new RegisterUser(true, "User " + name + " successfully registered");
        }
        return new RegisterUser(false, "User with name already exists");
    }
}
