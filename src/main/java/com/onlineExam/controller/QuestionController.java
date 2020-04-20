package com.onlineExam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.*;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class QuestionController {

    @Autowired
    private QuestionService ques;

    @Autowired
    private TeacherIndexService teacherIndexService;
    @RequestMapping("/Manager")
    public String Login(){
        return "ManagerIndex";
    }

    //进入试题管理页面
    @RequestMapping(value = "/testManage",method = RequestMethod.GET)
    public String goQuestion(Model model){
        List<Question> list=teacherIndexService.getAllQues();
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

    //试题删除
    @RequestMapping(value = "/delQuestionById" ,method =RequestMethod.GET)
    @ResponseBody
    public String delQuestionById1(@RequestParam("questionId") int questionId){
        int i=ques.delQuestionById(questionId);
        if (i==1){
            return "1";
        }
        return "2";
    }

   //试题查找 模糊查询
    @RequestMapping(value = "/queryQuestion",method = RequestMethod.GET)
    @ResponseBody
   public String queryQuestion(@RequestParam("questionName") String questionName){
        List<Question> list=ques.queryQuestion(questionName);
       if (list ==null){
           return "查找失败";
       }
        return "查找成功";
   }

    //查看之后 显示信息
    @RequestMapping(value = "/queryShow",method = RequestMethod.GET)
    public String queryShow(@RequestParam("questionName") String questionName,Model model){
        List<Question> list=ques.queryQuestion(questionName);
        model.addAttribute("list",list);
        return "QuestionManagement";
    }

    //试题修改页面弹出
    @RequestMapping(value = "/updateQues",method = RequestMethod.GET)
    public String editIndex(Integer questionId, Model model){
        Question question=ques.showQuestion(questionId);
        model.addAttribute("question",question);
        List<Course> courseList=ques.getCourse();
        model.addAttribute("courseList",courseList);
        List<Major> majorList=ques.getMajor();
        model.addAttribute("majorList",majorList);
        return "Question_edit";
    }

    //试题修改实现
    @RequestMapping(value = "/updateQuestion",method = RequestMethod.GET)
    @ResponseBody
    public String updateQues(Integer questionId, String questionName, String optionA, String optionB, String optionC, String optionD, String answer, Integer questionScore, Integer courseId, Integer majorId, String questionType, String questionClass){
        int i=ques.updateQuestion(questionId,  questionName,  optionA,  optionB,  optionC,  optionD,  answer,  questionScore,  courseId,  majorId,  questionType,  questionClass);
        if (i==1){
            return "1";
        }
        return "0";
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
@RequestMapping("getAllQuestion")
@ResponseBody
    public Page getAllQuestion (@RequestParam("majorId") Integer majorId,@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit){
        List<Question> list=ques.getAllQuestionByMajorId(majorId);
    PageHelper.startPage(page,limit);
    PageInfo pages=new PageInfo(list,limit);
    Page data=Page.success(pages.getTotal(),pages.getList());
    System.out.println(page+"------"+limit);
    System.out.println(pages);
    return data;
}
     //各个专业的题量
    @RequestMapping("getAllQuestionVo")
    @ResponseBody
    public List<QuestionVo> getAllQuestionVo(){
        List<QuestionVo> questionVos=ques.getAllQuestionVo();
        return  questionVos;
    }
    @RequestMapping("getEcharts")
    public String  getEcharts(){
        return "QuestionTypeEchart";
    }
    @RequestMapping("getAllQuestionType")
    @ResponseBody
    public List<QuestionVo> getAllQuestionType(){
        List<QuestionVo> questionVos=ques.getQuestionType();
        return  questionVos;
    }
    @RequestMapping("getUserInfo")
    @ResponseBody
    public List<CountModel> getUserInfo(){
        List<CountVo> countVo=ques.getUserInfo();
        System.out.println(countVo);
        Integer userNum=countVo.get(0).getTeacherNum();
        Integer teacherNum=countVo.get(0).getStuNum();
        CountModel countModel=new CountModel("学生",userNum);
        CountModel countModel2=new CountModel("老师",teacherNum);
        List<CountModel> countModels=new ArrayList<>();
        countModels.add(countModel);
        countModels.add(countModel2);
        return  countModels;
    }
    @RequestMapping("getResourceInfo")
    @ResponseBody
    public List<CountModel> getResourceInfo(){
        List<CountVo> countVo=ques.getResourceInfo();
        System.out.println(countVo);
        Integer questionNum=countVo.get(0).getQuestionNum();
        Integer releaseNum=countVo.get(0).getReleaseNum();
        Integer papNum=countVo.get(0).getPapNum();
        CountModel countModel=new CountModel("题目",questionNum);
        CountModel countModel2=new CountModel("试卷",papNum);
        CountModel countModel3=new CountModel("考试任务",releaseNum);
        List<CountModel> countModels=new ArrayList<>();
        countModels.add(countModel);
        countModels.add(countModel2);
        countModels.add(countModel3);
        return  countModels;
    }
    @RequestMapping("adminHomePage")
    public String adminHomePage(){
        return "adminHomePage";
    }







}
