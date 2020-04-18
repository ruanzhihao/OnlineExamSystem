package com.onlineExam.controller;

import com.onlineExam.domain.Student;
import com.onlineExam.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/*
* 教师端主页功能
* */
@Controller
public class TeacherIndexController {


    @Autowired
    private TeacherIndexService teacherIndexService;

@RequestMapping(value = "/studentInfo",method = RequestMethod.GET)
    public String goInfo(Model model){
    List<Student> list=teacherIndexService.getAllStudent();
    model.addAttribute("list",list);
        return "StudentInfo";
    }
}
