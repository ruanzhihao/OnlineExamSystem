package com.onlineExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentFunctionController {

    //学生端进入
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String goIndex(){

        return "StudentIndex";
    }
    //进入修改个人资料界面
    @RequestMapping(value = "/personal",method = RequestMethod.GET)
    public String goPer(){

        return "personInfo";
    }
//进入修改密码页面
    @RequestMapping(value = "/updatePass",method = RequestMethod.GET)
    public String goUpdate(){

        return "changepwd";
    }

    //进入我的成绩单
    @RequestMapping(value = "/myReport",method = RequestMethod.GET)
    public String goMyInfo(){

        return "myReport";
    }

    //学生端 默认界面
    @RequestMapping(value = "/goMain")
    public String goMain(){

        return "main";
    }
}
