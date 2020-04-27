package com.onlineExam.controller.student;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.domain.Student;
import com.onlineExam.mapper.StuUserMapper;
import com.onlineExam.modules.common.controller.MainController;
import org.apache.shiro.crypto.hash.Md5Hash;
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

//import com.onlineExam.service.Impl.UserServiceImpl;

@Controller
public class StuLoginController {
    @Autowired
    private StuUserMapper stuUserMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/login")
    public String login(){
        return "index";
    }

    @RequestMapping(value="/stuLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("password",password);

        Student student=new Student();
        student=stuUserMapper.findInformationByUsername(username);
        Integer stuid=student.getId();
        String stuname=student.getStuname();
        String stupassword=student.getStupassword();
        String stuphonenumber=student.getStuphonenumber();
        String stuemail=student.getStuemail();
        Integer clazzId=student.getClazzId();
        Integer departId=student.getDepartId();
        Integer stateId=student.getStateId();
        Integer majorId=student.getMajorId();

        request.getSession().setAttribute("stuid",stuid);
        request.getSession().setAttribute("stuname",stuname);
        request.getSession().setAttribute("stupassword",stupassword);
        request.getSession().setAttribute("stuphonenumber",stuphonenumber);
        request.getSession().setAttribute("stuemail",stuemail);
        request.getSession().setAttribute("clazzId",clazzId);
        request.getSession().setAttribute("departId",departId);
        request.getSession().setAttribute("stateId",stateId);
        request.getSession().setAttribute("majorId",majorId);

        Md5Hash md5registepassword=new Md5Hash( password, username,5);
         String md5password=md5registepassword.toString();
        System.out.println("登录账号："+username+"   密码："+password+"   加密后："+md5password);

        String roles=stuUserMapper.login(username,md5password);
        session.setAttribute("roles",roles);

        if (roles!=null&&flag.equals("true")){
            if(request.getParameter("rememberMe_stu")!=null){
                addCookie(username,password,response,request);
            }
            return "StudentIndex";
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
    @RequestMapping(value = "/getStuCookie",method = RequestMethod.POST)
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
