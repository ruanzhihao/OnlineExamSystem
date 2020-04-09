package com.onlineExam.controller.student;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.mapper.StuUserMapper;
import com.onlineExam.modules.common.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

//import com.onlineExam.service.Impl.UserServiceImpl;

@Controller
public class StuLoginController {
    @Autowired
    private StuUserMapper  stuUserMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    @RequestMapping(value="/stuLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request,HttpSession session,@RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("账号："+username+"密码："+password);

        String roles=stuUserMapper.login(username,password);
        session.setAttribute("roles",roles);
        if (roles!=null&&flag.equals("true")){
            return "loginsuccess";
        }else{
            return "index";
        }
    }


}
