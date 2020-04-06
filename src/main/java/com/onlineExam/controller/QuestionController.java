package com.onlineExam.controller;

import com.onlineExam.domain.Question;
import com.onlineExam.service.QuestionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService ques;

    @RequestMapping(value = "/testManage",method = RequestMethod.GET)
    public String goQuestion(Model model){
       List<Question> list=ques.getAllQuestion();
        model.addAttribute("list",list);
        return "QuestionManagement";
    }

}
