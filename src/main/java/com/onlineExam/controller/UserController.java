package com.onlineExam.controller;

import com.onlineExam.domain.User;
import com.onlineExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "test")
    public String getTestRes(Model model){
        /*model.addAttribute("test","测试");*/
        return "Test";
    }
    @RequestMapping("getUser")
    @ResponseBody
    public User getUser(String name){
        User user =userService.selectUser(name);
        return  user;
    }

    @RequestMapping("addUser")
    @ResponseBody
    public String addUser(User user){
        int row=userService.addUser(user);
        if (row>0){
            return "插入成功";
        }else {
            return "插入失败";
        }
    }



}
