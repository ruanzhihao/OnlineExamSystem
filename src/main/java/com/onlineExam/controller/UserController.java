package com.onlineExam.controller;

import com.onlineExam.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    @RequestMapping(value="/loginPage",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request,HttpSession session){

        String uid=request.getParameter("uid");
        String password=request.getParameter("password");
        System.out.println("账号："+uid+"密码："+password);

        String uname=userServiceImpl.login(uid,password);
        session.setAttribute("uname",uname);
        if (uname==null){
            return "index";
        }else{
            return "login";
        }

    }
}
