package com.onlineExam.controller;

import com.onlineExam.domain.Teacher;
import com.onlineExam.modules.common.controller.MainController;
import com.onlineExam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //进去教师管理界面
    @RequestMapping(value = "/teenager",method = RequestMethod.GET)
    public String getTeacherList(Model model){
        List<Teacher> list=teacherService.getAllTeacher();
        model.addAttribute("list",list);
        return "TeacherManagement";
    }
    //添加老师
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    @ResponseBody
    public String addTeacher(@RequestBody Teacher teacher){
        int a=teacherService.addTeacher(teacher);
        return "TeacherManagement";
    }

    //删除教师信息
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public String deleteTeacher(Integer teacherid){
        int s=teacherService.deleteTeacher(teacherid);
        return "TeacherManagement";
    }

    //修改教师信息
    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
    public String updateTeacher(Teacher teacher){
        int t=teacherService.updateTeacher(teacher);
        return "TeacherManagement";//测试
    }

    //根据id查找学生
    @RequestMapping("/findTeacherById")
    public String findTeacherById(Integer teacherid, HttpSession session){
        Teacher t=teacherService.findTeacherById(teacherid);
        session.setAttribute("t",t);
        return "Teacher_edit";
    }

    @RequestMapping(value="/teacherlogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session, @RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String teachername=request.getParameter("teachername");
        String teacherpassword=request.getParameter("teacherpassword");
        System.out.println("账号："+teachername+"密码："+teachername);

        String tname=teacherService.login(teachername,teacherpassword);
        session.setAttribute("tname",tname);
        if (tname!=null&&flag.equals("true")){
            return "loginsuccess";
        }else{
            return "index";
        }
    }
}
