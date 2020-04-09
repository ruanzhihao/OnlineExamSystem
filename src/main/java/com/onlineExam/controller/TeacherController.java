package com.onlineExam.controller;

import com.onlineExam.domain.Teacher;
import com.onlineExam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    //进去教师管理界面
    @RequestMapping(value = "/teenager")
    public String getTeacherList(Model model){
        List<Teacher> list=teacherService.getAllTeacher();
        model.addAttribute("list",list);
        return "TeacherManagement";
    }
    //添加老师
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    @ResponseBody
    public String addTeacher(@RequestBody Teacher teacher){
        //teacher.setTeacherpassword(MD5Util.MD5EncodeUtf8(teacher.getTeacherpassword()));
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
        //teacher.setTeacherpassword(MD5Util.MD5EncodeUtf8(teacher.getTeacherpassword()));
        int t=teacherService.updateTeacher(teacher);
        return "redirect:/teenager";
    }

    //根据id查找学生
    @RequestMapping("/findTeacherById")
    public String findTeacherById(Integer teacherid, HttpSession session){
        Teacher t=teacherService.findTeacherById(teacherid);
        session.setAttribute("t",t);
        return "Teacher_edit";
    }
    //教师查找
    @RequestMapping(value = "/queryTeacher",method = RequestMethod.GET)
    @ResponseBody
    public String queryTeacher(@RequestParam("teachername") String teachername){
        List<Teacher> list=teacherService.queryTeacher(teachername);
        if (list ==null){
            return "查找失败";
        }else{
            return "查找成功";
        }
    }

    //查找后的显示信息
    @RequestMapping(value = "/queryShowTeacher",method = RequestMethod.GET)
    public String queryShow(@RequestParam("teachername") String teachername,Model model){
        List<Teacher> list=teacherService.queryTeacher(teachername);
        model.addAttribute("list",list);
        return "TeacherManagement";
    }
}
