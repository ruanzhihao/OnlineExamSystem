package com.onlineExam.controller;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;
import com.onlineExam.service.QuestionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService ques;

    @RequestMapping("/Manager")
    public String Login(){
        return "Test";
    }

    //进入试题管理页面
    @RequestMapping(value = "/testManage",method = RequestMethod.GET)
    public String goQuestion(Model model){
        List<Question> list=ques.getAllQuestion();
        model.addAttribute("list",list);
        List<Course> courseList=ques.getCourse();
        model.addAttribute("courseList",courseList);
        List<Major> majorList=ques.getMajor();
        model.addAttribute("majorList",majorList);
        return "QuestionManagement";
    }

    //试题增加
    @RequestMapping(value = "/addQuestion" ,method =RequestMethod.GET)
    @ResponseBody
    public String addQuestion(@RequestParam("questionName")String questionName, @RequestParam("optionA")String optionA, @RequestParam("optionB")String optionB,
                              @RequestParam("optionC")String optionC, @RequestParam("optionD")String optionD, @RequestParam("answer")String answer,
                              @RequestParam("questionScore")int questionScore, @RequestParam("courseId")int courseId, @RequestParam("majorId")int majorId,
                              @RequestParam("questionType")String questionType, @RequestParam("questionClass")String questionClass){
        int i=ques.addQuestion(questionName,optionA,optionB,optionC,optionD,answer,questionScore,courseId,majorId,questionType,questionClass);
        if (i==0){
            return "添加失败";
        }
        return "添加成功";
    }

}
