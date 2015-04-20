package com.IMaylatov.Recommend.Logic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author Ivan Maylatov (IMaylatov@gmail.com
 * date: 20.04.2015
 */
@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "view";
    }
}
