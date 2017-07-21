package com.kerry.estate.owner.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangshen on 2017/7/21.
 */
@Controller
@RequestMapping("/api/estate/demo")
public class Demo {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "redirect:http://192.168.25.110:8080?123123";
    }
}
