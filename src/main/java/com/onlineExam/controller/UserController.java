package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping(value = "test")
    public String getTestRes(Model model){
        /*model.addAttribute("test","测试");*/
        return "index";
    }
}
