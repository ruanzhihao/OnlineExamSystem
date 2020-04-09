package com.onlineExam.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.modules.common.controller.MainController;
import com.onlineExam.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @RequestMapping("/login1")
    public String login(){
        return "index";
    }

   /* @RequestMapping(value="/loginPage",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request,HttpSession session,@RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String uid=request.getParameter("uid");
        String password=request.getParameter("password");
        System.out.println("账号："+uid+"密码："+password);

        String userid=userServiceImpl.login(uid,password);
        session.setAttribute("userid",userid);
        if (userid!=null&&flag.equals("true")){
            return "loginsuccess";
        }else{
            return "index";
        }
    }*/


}
