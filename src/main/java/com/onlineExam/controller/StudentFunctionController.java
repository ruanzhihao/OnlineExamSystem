package com.onlineExam.controller;

import com.onlineExam.domain.Paper;
import com.onlineExam.domain.Question;
import com.onlineExam.service.PaperService;
import com.onlineExam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
 * 学生端主页
 * */
@Controller
public class StudentFunctionController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;
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
    //进入参加考试界面  获取待考试 并显示
    @RequestMapping(value = "/JoinExam",method = RequestMethod.GET)
    public String goExam(Model model){
        List<Paper> list=paperService.getAllPaper();
        model.addAttribute("PaperList",list);
        return "JoinExam";
    }
    //点击考试界面 进入考试 获取试卷试题 并显示-----后去换生成随机数获取题目
    @RequestMapping(value = "/Examing",method = RequestMethod.GET)
    public String goExaming(Model model, @RequestParam("number") String number, @RequestParam("score")String score){
        model.addAttribute("number",number);
        model.addAttribute("score",score);

        List<Question> list=questionService.getAllQuestion();

        Question question=list.get(0);
        model.addAttribute("question1",question);

        Question question2=list.get(1);
        model.addAttribute("question2",question2);

        Question question3=list.get(2);
        model.addAttribute("question3",question3);

        Question question4=list.get(3);
        model.addAttribute("question4",question4);
        return "Examing";
    }

    //点击交卷自动判分 实现
    @RequestMapping(value = "/jiaojuan",method = RequestMethod.POST)
    @ResponseBody
    public int jiaojuan( @RequestParam("answer1") String answer1,@RequestParam("answer2")String answer2,@RequestParam("answer3")String answer3, @RequestParam("answer4")String answer4,
                         @RequestParam("ranswer1")String  ranswer1, @RequestParam("ranswer2")String ranswer2,@RequestParam("ranswer3")String  ranswer3, @RequestParam("ranswer4")String ranswer4,
                         @RequestParam("score1")int score1, @RequestParam("score2")int score2, @RequestParam("score3")int score3, @RequestParam("score4")int score4){
        int sum=0;
        if(answer1.equals(ranswer1)&&answer1!=null){sum=score1;}
        if(answer2.equals(ranswer2)&&answer2!=null){sum=sum+score2;}
        if(answer3.equals(ranswer3)&&answer3!=null){sum=sum+score3;}
        if(answer4.equals(ranswer4)&&answer4!=null){sum=sum+score4;}
        System.out.println(sum);


        return sum;
    }

    //自动交卷判分
    @RequestMapping(value = "/AutoJiaoJuan",method = RequestMethod.POST)
    @ResponseBody
    public int AutoJiaojuan( @RequestParam("answer1") String answer1,@RequestParam("answer2")String answer2,@RequestParam("answer3")String answer3, @RequestParam("answer4")String answer4,
                             @RequestParam("ranswer1")String  ranswer1, @RequestParam("ranswer2")String ranswer2,@RequestParam("ranswer3")String  ranswer3, @RequestParam("ranswer4")String ranswer4,
                             @RequestParam("score1")int score1, @RequestParam("score2")int score2, @RequestParam("score3")int score3, @RequestParam("score4")int score4){
        int sum=0;
        if(answer1.equals(ranswer1)&&answer1!=null){sum=score1;}
        if(answer2.equals(ranswer2)&&answer2!=null){sum=sum+score2;}
        if(answer3.equals(ranswer3)&&answer3!=null){sum=sum+score3;}
        if(answer4.equals(ranswer4)&&answer4!=null){sum=sum+score4;}
        System.out.println(sum);

        return sum;
    }








}
