package com.onlineExam.controller;

import com.onlineExam.domain.Teacher;
import com.onlineExam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


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



}
