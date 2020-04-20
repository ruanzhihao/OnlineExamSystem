package com.onlineExam.controller;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;
import com.onlineExam.domain.Student;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
* 教师端主页功能
* */
@Controller
public class TeacherIndexController {


    @Autowired
    private TeacherIndexService teacherIndexService;

    @Autowired
    private QuestionService ques;


    @RequestMapping(value = "/QuestionInfo",method = RequestMethod.GET)
    public String goInfo(Model model){
        List<Question> list=teacherIndexService.getAllQues();
        model.addAttribute("quesList",list);

        List<Course> courseList=ques.getCourse();
        model.addAttribute("courseList",courseList);

        List<Major> majorList=ques.getMajor();
        model.addAttribute("majorList",majorList);
        return "QuestionInfo";

    }
    @RequestMapping(value = "/queryQuestionByCourseId",method = RequestMethod.POST)
    @ResponseBody
    public String queryQuestionByCourseId(Model model,@RequestParam("courseId") int courseId){
    List<Question> tgQues=teacherIndexService.getTgQues(courseId);
    if (tgQues == null){
        return "1";
    }
        return "2";
    }

    @RequestMapping(value = "/queryByCourseId",method = RequestMethod.GET)
    public String queryByCourseId(Model model,@RequestParam("courseId") int courseId){

        List<Question> tgQues=teacherIndexService.getTgQues(courseId);
        model.addAttribute("quesList", tgQues);

        List<Course> courseList=ques.getCourse();
        model.addAttribute("courseList",courseList);

        List<Major> majorList=ques.getMajor();
        model.addAttribute("majorList",majorList);

        return "QuestionInfo";

    }


    @RequestMapping(value = "/queryStuInfo",method = RequestMethod.POST)
    @ResponseBody
    public String queryStuInfo(@RequestParam("stuId") int stuId, Model model){
        List<Student> studentList=teacherIndexService.queryStuInfo(stuId);
      /*  model.addAttribute("list",studentList);*/
        return "studentInfo";
    }

    @RequestMapping(value = "/showStu",method = RequestMethod.GET)
    public String showInfo(Model model,@RequestParam("stuId") int stuId){
    List<Student> list=teacherIndexService.queryStuInfo(stuId);
    model.addAttribute("list",list);
        return "QuestionInfo";
    }
}
