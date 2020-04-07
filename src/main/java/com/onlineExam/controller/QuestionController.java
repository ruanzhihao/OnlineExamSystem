package com.onlineExam.controller;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.FileUpload;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;
import com.onlineExam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    //导入试题
    @RequestMapping(value = "getImport")
    public String getImport(Model model){
        List<Course> courses=  ques.getAllCourse();
        List<Major>  majors=ques.getAllMajor();
        model.addAttribute("courses",courses);
        model.addAttribute("majors",majors);
        return "importExcel";
    }
    //获取数据
    @RequestMapping(value = "getUpload",method = RequestMethod.POST)
    public String getUpload(HttpServletRequest request, @RequestParam("excelFile")MultipartFile excelFile, @RequestParam("courseId") Integer courseId, @RequestParam("majorId") Integer  majorId, Model model){
        String savePath="";
        /*ModelAndView mv=new ModelAndView("testManage");*/
        try{
            savePath=saveFile(excelFile,request.getRealPath(""));
            List<Question> questions=FileUpload.getFile(savePath,courseId,majorId);
            System.out.println(questions);
            Map<String,Object> questionsMap= new HashMap<String, Object>();
            questionsMap.put("questions",questions);
            //addQuestion(questions,questionsMap);
            for (Question q: questions)
            {
                System.out.println(q);
                System.out.println(q.getAnswer());
                ques.addImportQuestion(null,q.getQuestionName(),q.getOptionA(),q.getOptionB(),q.getOptionC(),q.getOptionD(),q.getAnswer(),q.getQuestionScore(),q.getCourseId(),q.getMajorId(),q.getQuestionType(),q.getQuestionClass());
            }
            if (questions.size() == 0) {
                model.addAttribute("success","格式有问题");
            } else {
                model.addAttribute("success", "共添加 "+questions.size()+" 道题");
                System.out.println("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("error");
            System.out.println("Error");
            modelAndView.addObject("error", "上传失败");
        } finally {
            deleteUploadFile(savePath);
        }
        return "redirect:testManage";
    }
    private String saveFile(MultipartFile file, String rootPath) {
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(rootPath+fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootPath+fileName;
    }
    private void deleteUploadFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }

}
