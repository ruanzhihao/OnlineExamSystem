package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String Login(){
        return "index";
    }

    @RequestMapping("/test2")
    public String Test2(){
        return "Test2";
    }


}
