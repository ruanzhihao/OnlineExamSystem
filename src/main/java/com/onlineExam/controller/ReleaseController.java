package com.onlineExam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.*;
import com.onlineExam.service.PaperService;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.ReleaseExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReleaseController {
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReleaseExamService releaseExamService;
@RequestMapping("getReleaseForm")
    public String getReleaseForm(Model model){
    List<Depart> departs=releaseExamService.getAllDepart();
    List<Course> courses=questionService.getAllCourse();
    List<Major>  majors=questionService.getAllMajor();
    List<Paper>  papers=releaseExamService.getPaperName();
    model.addAttribute("courses",courses);
    model.addAttribute("papers",papers);
    model.addAttribute("majors",majors);
   model.addAttribute("departs",departs);
   return "releaseExam";
}
   @RequestMapping("getReleaseInfo")
        public String getReleaseInfoForm(@RequestParam("departId")Integer departId , @RequestParam("majorId") Integer majorId,
                                         @RequestParam("courseId")Integer courseId, @RequestParam("beginTime") String beginTime,
                                         @RequestParam("authorId") Integer authorId, @RequestParam("paperId") Integer paperId){
       System.out.println(beginTime);
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       int i=releaseExamService.releaseExam(departId ,majorId,courseId,beginTime,authorId,paperId);
       System.out.println("发布成功");
       return "redirect:releaseInfo";
   }
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss" );
        dateFormat.setLenient( false );
        binder.registerCustomEditor( Date.class, new CustomDateEditor( dateFormat, false ) );
    }

    @RequestMapping("releaseInfoCenter")
    public String releaseInfo(Model model){
             List<ExamState> states=releaseExamService.getAllState();
             model.addAttribute("states",states);
             return "releaseInfo";
    }

    @RequestMapping("releaseInfo")
    @ResponseBody
    public Page releaseInfo(@RequestParam(value = "page",defaultValue = "1") Integer page){
          int limit=10;
          List<ReleaseExam> releaseExams=releaseExamService.getAllReleaseInfo();
          PageHelper.startPage(page,limit);
          PageInfo pageInfo=new PageInfo(releaseExams,limit);
          Page pages = Page.success(pageInfo.getTotal(), pageInfo.getList());
          return pages;
    }
    @RequestMapping("deleteReleaseInfoById")
    @ResponseBody
    public Msg deleteReleaseInfoById(@RequestParam("releaseExamId") Integer releaseExamId){
       int i=releaseExamService.deleteReleaseInfoById(releaseExamId);
       Msg succ=Msg.success().add("msg","删除成功");
       Msg fail=Msg.fail().add("msg","删除失败");
       if (i>0){
                return  succ;
       }else {
           return fail;
       }
    }
    @RequestMapping("getEditReleaseInfo")
    public String getEditReleaseInfo(@RequestParam("releaseExamId") Integer releaseExamId, Model model){
          ReleaseExam releaseExam=releaseExamService.getReleaseExamById(releaseExamId);
        System.out.println(releaseExam);
        List<Depart> departs=releaseExamService.getAllDepart();
        System.out.println(departs);
        List<Course> courses=questionService.getAllCourse();
        List<Major>  majors=questionService.getAllMajor();
        List<Paper>  papers=releaseExamService.getPaperName();
        model.addAttribute("courses",courses);
        model.addAttribute("papers",papers);
        model.addAttribute("majors",majors);
        model.addAttribute("departs",departs);
        model.addAttribute("releaseExam",releaseExam);
         return "editReleaseForm";
    }
    @RequestMapping("editReleaseInfo")
    @ResponseBody
    public Msg editReleaseInfo(@RequestParam("departId")Integer departId , @RequestParam("majorId") Integer majorId,
                               @RequestParam("courseId")Integer courseId, @RequestParam("beginTime") String beginTime,
                               @RequestParam("authorId") Integer authorId, @RequestParam("paperId") Integer paperId,@RequestParam("releaseExamId") Integer releaseExamId){
              Msg msg=Msg.success().add("msg","更新成功");
              Msg fail=Msg.fail().add("msg","更新失败");
              int i=releaseExamService.updateReleaseExam(releaseExamId,departId,majorId,courseId,beginTime,authorId,paperId);
        System.out.println(i);
              if(i>0){
                    return msg;
              }else {
                  return fail;
              }
    }
    @RequestMapping("searchRelease")
    @ResponseBody
    public  Page getReleaseByStateId(@RequestParam("examStateId") Integer examStateId,@RequestParam("page") Integer page){
      List<ReleaseExam> lists=releaseExamService.getReleaseInfoByStateId(examStateId);
      int limit=10;
      PageHelper.startPage(page,limit);
      PageInfo pages=new PageInfo(lists,limit) ;
      Page pa=Page.success(pages.getTotal(),pages.getList());
      return  pa;
}
}
