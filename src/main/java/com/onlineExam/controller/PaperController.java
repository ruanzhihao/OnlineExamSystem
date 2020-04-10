package com.onlineExam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.*;
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

@Controller
public class PaperController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private  PaperService paperService;
    @RequestMapping("getAddPaper")
    public String getPaperCenter(Model model){
      List<Major> majors = questionService.getAllMajor();
        model.addAttribute("majors",majors);
        return "addPaper";
    }

    @RequestMapping(value = "addPapers",method = RequestMethod.POST)
    public String addPaper(@RequestParam("paperName") String paperName,@RequestParam("majorId") Integer majorId,@RequestParam("answerTime") int answerTime,@RequestParam("paperClass") String paperClass){
        System.out.println(paperName);
        System.out.println(paperClass);
        System.out.println(answerTime);
        System.out.println(majorId);
       int row=paperService.addPaperService(paperName,paperClass,34,answerTime,50,majorId);
       if(row==1){
           System.out.println("添加成功");
       }
       return "redirect:paperCenter";
    }
    @RequestMapping(value = "paperCenter",method = RequestMethod.GET)
    public  String paperCenter(Model model){
        List<Paper> papers=paperService.getAllPaper();
        model.addAttribute("papers",papers);
        List<Major> majors=paperService.getAllMajor();
        model.addAttribute("major",majors);
        return "paperList";
    }

    @RequestMapping(value = "/groupPaper", method = RequestMethod.GET)
    @ResponseBody
    public Page page_list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit){
        PageHelper.startPage(page, limit);
        List<Paper> papers=paperService.getAllPaper();
        PageInfo pages = new PageInfo(papers, limit);
        System.out.println(pages.getList());
        return Page.success(pages.getTotal(), pages.getList());
    }
    @RequestMapping(value = "/deletePaper", method = RequestMethod.GET)
    @ResponseBody
    public Msg delete_one(@RequestParam("paperId") Integer paperId){
        int row=paperService.deletePaperById(paperId);
        return Msg.success().add("msg", "删除成功！");
    }
    @RequestMapping("getPapEdit")
      public String getPapEdit(@RequestParam("paperId")Integer paperId, Model model){
          Paper paper= paperService.findPaperById(paperId);
          List<Major> majors=paperService.getAllMajor();
          List<EasyClass> easyClass=paperService.getAllEasyClass();
          model.addAttribute("majors",majors);
          model.addAttribute("paper",paper);
          model.addAttribute("easyClass",easyClass);
          return "edit";
    }
    @RequestMapping(value = "/editPaper", method = RequestMethod.POST)
    @ResponseBody
    public Msg edit_sub(Paper paper){
        paperService.updatePaper(paper);
        return Msg.success().add("msg", "更新成功！");
    }
    @RequestMapping(value = "/searchPapers", method = RequestMethod.GET)
    @ResponseBody
    public Page search_sub(@RequestParam("majorId") Integer majorId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "9") Integer limit){

        PageHelper.startPage(page, limit);
        List<Paper> lists=paperService.findPaperByMajorId(majorId);
        PageInfo pages = new PageInfo(lists, limit);
        return Page.success(pages.getTotal(), pages.getList());
    }
}
