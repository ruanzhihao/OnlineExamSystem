package com.onlineExam.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.mapper.AdminMapper;
import com.onlineExam.modules.common.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping(value="/adminLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("账号："+username+"密码："+password);

        String roles=adminMapper.login(username,password);
        //System.out.println(roles);
        session.setAttribute("roles",roles);
        if (roles!=null&&flag.equals("true")){
            if(request.getParameter("rememberMe_admin")!=null){
                addCookie(username,password,response,request);
            }
            return "ManagerIndex";

        }else{
            return "index";
        }
    }
    public static void addCookie(String username, String password, HttpServletResponse response, HttpServletRequest request)  {
        //创建cookie
        Cookie nameCookie = new Cookie(username, password);
        nameCookie.setPath("/");//设置cookie路径
        //设置cookie保存的时间 单位：秒
        nameCookie.setMaxAge(60*60*24);
        //将cookie添加到响应
        response.addCookie(nameCookie);
    }

    @ResponseBody
    @RequestMapping(value = "/getAdminCookie",method = RequestMethod.POST)
    public Map<String,String> initCookie(String username, HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        Map<String,String> map=new HashMap<>();
        for (Cookie c:cookies){
            if(c.getName().equals(username)){
                String password=c.getValue();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        }
        return null;
    }

}
