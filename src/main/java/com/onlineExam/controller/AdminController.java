package com.onlineExam.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.mapper.AdminMapper;
import com.onlineExam.modules.common.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value="/adminLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session, @RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("账号："+username+"密码："+password);

        String roles=adminMapper.login(username,password);
        System.out.println(roles);
        session.setAttribute("roles",roles);
        if (roles!=null&&flag.equals("true")){

            return "ManagerIndex";

        }else{
            return "index";
        }
    }
}
