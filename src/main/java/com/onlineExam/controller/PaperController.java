package com.onlineExam.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.*;
import com.onlineExam.service.PaperService;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.RealPaperService;
import org.apache.ibatis.annotations.Param;
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
public class PaperController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private RealPaperService realPaperService;

    @RequestMapping("getAddPaper")
    public String getPaperCenter(Model model) {
        List<Major> majors = questionService.getAllMajor();
        model.addAttribute("majors", majors);
        return "addPaper";
    }

    @RequestMapping(value = "addPapers", method = RequestMethod.POST)
    public String addPaper(@RequestParam("paperName") String paperName, @RequestParam("majorId") Integer majorId, @RequestParam("answerTime") int answerTime, @RequestParam("paperClass") String paperClass) {
        System.out.println(paperName);
        System.out.println(paperClass);
        System.out.println(answerTime);
        System.out.println(majorId);
        int row = paperService.addPaperService(paperName, paperClass, 0, answerTime, 0, majorId);
        if (row == 1) {
            System.out.println("添加成功");
        }
        return "redirect:paperCenter";
    }

    @RequestMapping(value = "paperCenter", method = RequestMethod.GET)
    public String paperCenter(Model model) {
        List<Paper> papers = paperService.getAllPaper();
        model.addAttribute("papers", papers);
        List<Major> majors = paperService.getAllMajor();
        model.addAttribute("major", majors);
        return "paperList";
    }

    @RequestMapping(value = "/groupPaper", method = RequestMethod.GET)
    @ResponseBody
    public Page page_list(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "7") Integer limit) {
        PageHelper.startPage(page, limit);
        List<Paper> papers = paperService.getAllPaper();
        PageInfo pages = new PageInfo(papers, limit);
        System.out.println(pages.getList());
        return Page.success(pages.getTotal(), pages.getList());
    }

    @RequestMapping(value = "/deletePaper", method = RequestMethod.GET)
    @ResponseBody
    public Msg delete_one(@RequestParam("paperId") Integer paperId) {
        int row = paperService.deletePaperById(paperId);
        return Msg.success().add("msg", "删除成功！");
    }

    @RequestMapping("getPapEdit")
    public String getPapEdit(@RequestParam("paperId") Integer paperId, Model model) {
        Paper paper = paperService.findPaperById(paperId);
        List<Major> majors = paperService.getAllMajor();
        List<EasyClass> easyClass = paperService.getAllEasyClass();
        model.addAttribute("majors", majors);
        model.addAttribute("paper", paper);
        model.addAttribute("easyClass", easyClass);
        return "edit";
    }

    @RequestMapping(value = "/editPaper", method = RequestMethod.POST)
    @ResponseBody
    public Msg edit_sub(Paper paper) {
        paperService.updatePaper(paper);
        return Msg.success().add("msg", "更新成功！");
    }

    @RequestMapping(value = "/searchPapers", method = RequestMethod.GET)
    @ResponseBody
    public Page search_sub(@RequestParam("majorId") Integer majorId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "9") Integer limit) {

        PageHelper.startPage(page, limit);
        List<Paper> lists = paperService.findPaperByMajorId(majorId);
        PageInfo pages = new PageInfo(lists, limit);
        return Page.success(pages.getTotal(), pages.getList());
    }

    @RequestMapping(value = "getAddIntoPap", method = RequestMethod.GET)
    public String getAddIntoPap(@RequestParam("paperId") Integer paperId, Model model) {
        Paper paper = paperService.findPaperById(paperId);
        List<Major> majors = paperService.getAllMajor();
        List<EasyClass> easyClass = paperService.getAllEasyClass();
        List<Course> courses = paperService.getAllCourse();
        model.addAttribute("courses", courses);
        model.addAttribute("majors", majors);
        model.addAttribute("paper", paper);
        model.addAttribute("easyClass", easyClass);
        return "addQuesIntoPap";
    }

    @GetMapping(value = "imp_addIntoPaper")
    public String getImport() {
        return "redirect:imp_addIntoPaper";
    }

    @RequestMapping(value = "imp_addIntoPaper", method = RequestMethod.POST)
    public String getUpload(HttpServletRequest request, @RequestParam("excelFile") MultipartFile excelFile, @RequestParam("courseId") Integer courseId, @RequestParam("majorId") Integer majorId,
                            @Param("paperId") Integer paperId, Model model) {
        ModelAndView mv = new ModelAndView();
        String savePath = "";
        try {
            savePath = saveFile(excelFile, request.getSession().getServletContext().getRealPath(""));
            List<Question> questions = FileUpload.getFile(savePath, courseId, majorId);
            System.out.println(questions);
            addIntoPaper(questions, paperId);
            if (questions.size() == 0) {
                model.addAttribute("success", "格式有问题");
            } else {
                model.addAttribute("success", "共添加 " + questions.size() + " 道题");
                System.out.println("Success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("error");
            mv.addObject("error", "上传失败");
        } finally {
            deleteUploadFile(savePath);
        }
        return "redirect:paperCenter";
    }

    private void addIntoPaper(List<Question> questions, Integer paperId) {
        List<Integer> questionIds = new ArrayList<Integer>();
        //int count = realPaperService.countQuestionNumber(paperId);
        int count=0;
      //  int score = paperService.findSumquestionScore(paperId);
        int score=0;
        for (Question q : questions) {
            questionService.addImportQuestion(null, q.getQuestionName(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD(), q.getAnswer(), q.getQuestionScore(), q.getCourseId(), q.getMajorId(), q.getQuestionType(), q.getQuestionClass());
            score += q.getQuestionScore();
            System.out.println("此时添加的总分值"+score);
            /*questionIds.add(q.getQuestionId());*/
            count++;
            System.out.println("此时添加的数量"+count);
        }
        List<Integer> listIds = questionService.getQuestionId(count);
        questionIds.addAll(listIds);
        System.out.println(questionIds);
        Map<String, Object> realPap = new HashMap<String, Object>();
        realPap.put("paperId", paperId);
        realPap.put("questionIds", questionIds);
        realPaperService.addRealPaper(realPap);


        //修改试卷信息
        Map<String, Object> updateInfo = new HashMap<String, Object>();
        updateInfo.put("questionNumber", count);
        updateInfo.put("score", score);
        updateInfo.put("paperId", paperId);
        paperService.updatePaperScore(updateInfo);
        paperService.updatePaperQuestionNumber(updateInfo);
    }

    private String saveFile(MultipartFile file, String rootPath) {
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(rootPath + fileName));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootPath + fileName;
    }

    private void deleteUploadFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }


    @RequestMapping("getPapQues")
    public String getPapQuesList(@RequestParam("paperId") Integer paperId, Model model) {
        List<PapQuestion> questions = paperService.getPapQues(paperId);
        model.addAttribute("data", questions);
        return "paperQuestionList";
    }

    //查看题目详情
    @RequestMapping("getGroupPapQues")
    @ResponseBody
    public Page getGrouoPapQuesList(@RequestParam("paperId") Integer paperId, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "9") Integer limit, Model model) {
        List<PapQuestion> questions = paperService.getPapQues(paperId);
        PageHelper.startPage(page, limit);
        PageInfo list = new PageInfo(questions, limit);
        //return Page.success(list.getTotal(),list.getList());
        Page pages = Page.success(list.getTotal(), list.getList());
        model.addAttribute(pages);
        return pages;
    }

    @RequestMapping(value = "/delPapQuestionById", method = RequestMethod.GET)
    @ResponseBody
    public Msg delQuestionById1(@RequestParam("realPaperId") int realPaperId,@RequestParam("paperId") Integer paperId,@RequestParam("questionScore") Integer questionScore) {
        Msg msg=Msg.success().add("msg","删除成功");
        int count = realPaperService.countQuestionNumber(paperId);
        System.out.println("当前试卷数量"+count);
        int score = paperService.findSumquestionScore(paperId);
        System.out.println("当前试卷分数"+score);
        realPaperService.deletePapQues(realPaperId);
        count=count-1;
        score=score-questionScore;
        Map<String,Object> map=new HashMap<>();
        map.put("paperId",paperId);
        map.put("score",score);
        map.put("questionNumber",count);
        paperService.updatePaperQuestionNumber1Simpl(map);
        paperService.updatePaperScoreSimpl(map);
        return msg;
    }

    @RequestMapping("addOneQuesIntoPap")
    @ResponseBody
    public Msg addOneQuesIntoPap(@RequestParam("paperId") Integer paperId, @RequestParam("questionId") Integer questionId,@RequestParam("questionScore") Integer questionScore) {
        int count = realPaperService.countQuestionNumber(paperId);
        System.out.println("当前试卷数量"+count);
        int score = paperService.findSumquestionScore(paperId);
        System.out.println("当前试卷分数"+score);
        Msg msg = Msg.success().add("msg", "添加成功！");
        Msg msg1 = Msg.success().add("msg", "该试题已存在！！");
        RealPaper realPaper = realPaperService.selectOneUnique(paperId, questionId);
        System.out.println("还没有此项数据"+realPaper+"可插");
        if (realPaper == null) {
            int i=realPaperService.addOnePap(paperId, questionId);
           // score=score+questionScore;
          //  count=count+1;
            Map<String, Object> updateInfo = new HashMap<String, Object>();
            updateInfo.put("questionNumber", 1);
            updateInfo.put("score", questionScore);
            updateInfo.put("paperId", paperId);
            paperService.updatePaperScore(updateInfo);
            paperService.updatePaperQuestionNumber(updateInfo);
            System.out.println(i);
                return msg;
        } else  {
            return msg1;
        }
    }
   /* @GetMapping(value = "/add_allQues")
        public  String getAllQues(){
        return "redirect:add_allQues";
    }*/
    @RequestMapping(value = "/add_allQues",produces="application/json; utf-8")
    @ResponseBody
    public Msg addAllQues(@RequestParam("questions") String questions,@RequestParam("paperId") Integer paperId) {
        int count=0;
        int score=0;
        // Integer paperId=Integer.parseInt(pId);
        System.out.println(questions);
        //List<Question> questionList=(List<Question>) JSONObject.parseObject(questions,Question.class);
        List<Question> questionList=JSONArray.parseArray(questions,Question.class);
        System.out.println(questionList);
        List<Integer> newAdd=new ArrayList<>();
        List<Integer> alreadyExisit=new ArrayList<>();
        for (Question q:questionList) {
            Integer questionId=q.getQuestionId();
            Integer questionScore=q.getQuestionScore();
            RealPaper realPaper=realPaperService.selectOneUnique(paperId,questionId);
            if (realPaper==null){
                realPaperService.addOnePap(paperId,questionId);
                count++;
                score=score+questionScore;
                newAdd.add(questionId);
            }
            else {
                alreadyExisit.add(questionId);
            }
        }
        if(alreadyExisit.size()==questionList.size()){
            return Msg.success().add("msg","试题"+alreadyExisit+"已存在添加失败");
        }
        Map<String, Object> update = new HashMap<>();
        update.put("paperId", paperId);
        update.put("score", score);
        update.put("questionNumber", count);
        paperService.updatePaperScore(update);
        paperService.updatePaperQuestionNumber(update);
        String success=newAdd.size()>0 ? newAdd+"添加成功":"";
        String warn=alreadyExisit.size()>0 ? alreadyExisit+"已存在，不可重复添加":"";
        Msg msg = Msg.success().add("msg", success+warn);
        return msg;
    }
    /*public Msg addAllQues(@RequestParam("ids") String ids,@RequestParam("scores") String scores,@RequestParam("paperId") Integer paperId) {
        int count = 0;
        int score = 0;
        int flag=0;
        System.out.println(ids);
        System.out.println(scores);
        Msg msg = Msg.success().add("msg", "添加成功！");
        List<String> questionIds = Arrays.asList(ids.split(","));
        List<String> questionScores = Arrays.asList(scores.split(","));
        List<Integer> alreadyExisit=new ArrayList<>();
        try {
            for (String id : questionIds) {
                Integer questionId = Integer.parseInt(id);
                flag++;
                RealPaper realPaper = realPaperService.selectOneUnique(paperId, questionId);
                if (realPaper == null) {
                    int i = realPaperService.addOnePap(paperId, questionId);
                    System.out.println("当前索引："+flag);
                    count++;
                } else {
                    alreadyExisit.add(questionId);
                    if (flag == questionIds.size()) {
                        return Msg.success().add("msg", "试题" + alreadyExisit + "已存在，批量添加失败！！");
                    }
                }
            }
                for (String s : questionScores) {
                    Integer questionScore = Integer.parseInt(s);
                    score += questionScore;
                    System.out.println(score);
                }
                Map<String, Object> update = new HashMap<>();
                update.put("paperId", paperId);
                update.put("score", score);
                update.put("questionNumber", count);
                paperService.updatePaperScore(update);
                paperService.updatePaperQuestionNumber(update);
                return msg;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }*/


}
