package com.onlineExam.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.onlineExam.domain.*;
import com.onlineExam.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * 学生端主页
 * */
@Controller
public class StudentFunctionController {
    @Autowired
    private StudentFunctionService studentFunctionService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private TeacherIndexService teacherIndexService;

    @Autowired
    private StuUserService stuUserService;

    @Autowired
    private StudyService studyService;

    //学生端进入
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String goIndex(){

        return "student/index/StudentIndex";
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
    public String goMyInfo(Model model,HttpSession session){
        String stuname= (String) session.getAttribute("stuname");
        List<Exam> examList=stuUserService.findScoreBystuname(stuname);
        model.addAttribute("examList",examList);
        return "myReport";
    }
    //根据考试名查找
    @RequestMapping(value = "/findExamByexamName",method = RequestMethod.GET)
    @ResponseBody
    public String queryExam(@RequestParam("examName") String examName,HttpSession session){
        String stuname=(String)session.getAttribute("stuname");
        List<Exam> examList=stuUserService.findExamByexamName(examName,stuname);
        if (examList ==null){
            return "查找失败";
        }else{
            return "查找成功";
        }
    }
    //查找后的显示信息
    @RequestMapping(value = "/queryExam",method = RequestMethod.GET)
    public String queryShow_e(@RequestParam("examName") String examName,Model model,HttpSession session){
        String stuname=(String)session.getAttribute("stuname");
        List<Exam> examList=stuUserService.findExamByexamName(examName,stuname);
        model.addAttribute("examList",examList);
        return "myReport";
    }

    //学生端 默认界面
    @RequestMapping(value = "/goMain")
    public String goMain(){

        return "main";
    }
/*    //进入参加考试界面  获取待考试 并显示
    @RequestMapping(value = "/JoinExam",method = RequestMethod.GET)
    public String goExam(Model model){
        List<Paper> list=paperService.getAllPaper();
        model.addAttribute("PaperList",list);
        return "JoinExam";
    }*/
//进入参加考试界面  获取待考试 并显示
/*@RequestMapping(value = "/JoinExam",method = RequestMethod.GET)
public String goExam(Model model){
    List<ReleaseExam> releaseExams=studentFunctionService.getReleaseByMajor(1);
    model.addAttribute("releaseExams",releaseExams);
    return "JoinExam";
}*/
    //进入参加考试界面  获取待考试 并显示
//进入参加考试界面  获取待考试 并显示
/*
@RequestMapping(value = "/JoinExam",method = RequestMethod.GET)
    public String goExam(Model model,HttpSession session){
        int majorId=(int)session.getAttribute("majorId");
        List<ReleaseExam> releaseExams=studentFunctionService.getReleaseByMajor(majorId);
        model.addAttribute("releaseExams",releaseExams);
        return "JoinExam";
    }
*/

@RequestMapping(value = "JoinExam",method = RequestMethod.GET)
public  String goExam(Model model,HttpSession session){
    List<JoinExamInfo> joinExamInfo=new ArrayList<>();
    int majorId=(int)session.getAttribute("majorId");
    List<ReleaseExam> releaseExams=studentFunctionService.getReleaseByMajor(majorId);
    System.out.println("要发布的任务："+releaseExams);
    int stuId=(int)session.getAttribute("stuid");
    for (ReleaseExam r:releaseExams) {
        String exam=studentFunctionService.getExamInfoByStuId(stuId,r.getReleaseExamId());
        JoinExamInfo ExamInfo=new JoinExamInfo(r,exam);
        joinExamInfo.add(ExamInfo);
    }
    System.out.println("最终结合："+joinExamInfo);
    model.addAttribute("joinExamInfo",joinExamInfo);
    return "student/exam/joinExams";
}
    //进入参加考试界面  获取待考试 并显示

    @Scheduled(cron = "0/3 * * * * *")
/*@RequestMapping("ddd")
@ResponseBody*/
    public void  getStuReleaseExam() throws Exception{
        List<ReleaseExam> releaseExams=studentFunctionService.selectReleaseTime();
        System.out.println(releaseExams);
        for (ReleaseExam r:releaseExams) {
            String beginTime=r.getBeginTime();
            System.out.println(beginTime);
            Integer answerTime=r.getAnswerTime();
            System.out.println(answerTime);
            int stateId=checkRelease(beginTime,answerTime);
            int i=studentFunctionService.updateState(r.getReleaseExamId(),stateId);
            System.out.println(i);
            if(i==1){
                System.out.println("更新任务"+r.getReleaseExamId()+"状态为"+stateId);            }
        }
    }

    public int checkRelease(String beginTime,int answerTime)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginTimeDate = sdf.parse(beginTime);
        Long beginTimes = beginTimeDate.getTime();
        int answerTimeSecond =  answerTime * 60 * 1000;
        Date nowDate = new Date();
        Long nowDateTime = nowDate.getTime();
        Long newAnswerTime = nowDateTime - beginTimes;
        System.out.println("时间差"+newAnswerTime);
        if (newAnswerTime.intValue() >= 0 && newAnswerTime.intValue() <= answerTimeSecond) {
            return 2;
        } else if (newAnswerTime.intValue() >answerTimeSecond) {
            return 3;
        } else {
            return 1;
        }
    }
    @RequestMapping(value = "/stuExam",method = RequestMethod.GET)
    public String getQuestion(@RequestParam("paperId") Integer paperId,@RequestParam("releaseExamId") Integer releaseExamId, Model model,HttpServletRequest request){
        int checkBoxScore=studentFunctionService.getCheckBoxQuestionScore(paperId);
        int checkBoxCount=studentFunctionService.getCheckBoxCount(paperId);
        List<Question> checkboxQuestions=studentFunctionService.getCheckQuestion(paperId);
        System.out.println("多选题量"+checkBoxCount+"多选分值"+checkBoxScore);
        System.out.println(checkboxQuestions);
        model.addAttribute("checkBoxQuestion",checkboxQuestions);
        model.addAttribute("checkBoxCount",checkBoxCount);
        model.addAttribute("checkBoxScore",checkBoxScore);
        int radioScore=studentFunctionService.getRadioQuestionScore(paperId);
        int radioCount=studentFunctionService.getRadioCount(paperId);
        List<Question> radioQuestions=studentFunctionService.getRadioQuestion(paperId);
        System.out.println("单选题数量"+radioCount+"单选题分值"+radioScore);
        System.out.println(radioQuestions);
        model.addAttribute("radioQuestions",radioQuestions);
        model.addAttribute("radioCount",radioCount);
        model.addAttribute("radioScore",radioScore);
        HttpSession session=request.getSession();
        session.setAttribute("paperId",paperId);
        session.setAttribute("releaseExamId",releaseExamId);
        System.out.println(session.getAttribute("paperId"));
        System.out.println(session.getAttribute("releaseExamId"));
        int answerTime=studentFunctionService.getAnswerTime(paperId);
        int examTime=answerTime*60;
        model.addAttribute("examTime",examTime);
        return "student/exam/exam";
    }
    @RequestMapping(value = "getScore",produces="application/json; utf-8")
    @ResponseBody
    public Integer getScore(@RequestParam("answers") String answers,@RequestParam("questionIds") String questionIds,HttpSession session){
        Integer releaseExamId=(Integer)session.getAttribute("releaseExamId");
        JSONArray answer=JSON.parseArray(answers);
        JSONArray questionId=JSON.parseArray(questionIds);
        List<String> str1=JSONObject.parseArray(answer.toJSONString(),String.class);
        List<Integer> str2=JSONObject.parseArray(questionId.toJSONString(),Integer.class);
        Integer stuId=(Integer) session.getAttribute("stuid");
        Integer paperId=(Integer) session.getAttribute("paperId");
        System.out.println(paperId);
        List<Map> record=new ArrayList<>();
        for (int i=0;i<str1.size();i++){
            Map map=new HashMap();
            map.put("questionId",str2.get(i));
            map.put("stuAnswer",str1.get(i));
            record.add(map);
            System.out.println(map);
        }
        Map<Object,Object> examRecord=new HashMap<>();
        examRecord.put("stuId",stuId);
        examRecord.put("releaseExamId",releaseExamId);
        examRecord.put("paperId",paperId);
        examRecord.put("record",record);

/*
        examRecord.put("questionId",str2);
*/
        /*examRecord.put("stuanswer",str1);*/
       /* for (String s:str1) {
            examRecord.put("stuAnswer",s);
        }*/
        int i=studentFunctionService.insertExamRecord(examRecord);
        System.out.println("插入"+i);
        Map<Object, Object> resultMap = new HashMap<>();
        Map<Object, Object> answerMap = new HashMap<>();
        List<StuAnswer> stuAnswers=studentFunctionService.useranswerRecord(paperId,releaseExamId);
        List<Question> questionAnswer=studentFunctionService.questionAnswer(paperId);
        List<Object> wrongAnswer=new ArrayList<>();
        for (StuAnswer stuanswer : stuAnswers) {
            answerMap.put(stuanswer.getQuestionId(), stuanswer);
        }
        for (Question questionresult : questionAnswer) {
            resultMap.put(questionresult.getQuestionId(), questionresult);
        }
        System.out.println(answerMap);
        int score = 0;
        Set<Object> keySet = answerMap.keySet();
        Iterator<Object> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Question question=(Question) resultMap.get(key);
            StuAnswer stuAnswer=(StuAnswer) answerMap.get(key);
            System.out.println(question);
            if (stuAnswer.getStuAnswer().equals(question.getAnswer())) {
                score=score+question.getQuestionScore();
            }else{
                wrongAnswer.add(key);
                //标记错题
                studentFunctionService.setWrong(stuAnswer.getStuAnswerId(),2);
            }
        }
        Student student=examService.fingStuById(stuId);
        Paper paper=examService.findPaperById(paperId);
        String examName=paper.getPaperName();
        String stuName=student.getStuname();
        String stuDept=student.getDepartName();
        String stuClazz=student.getClazzName();
        String examTime=new Date().toLocaleString();
        int row= examService.StuInsertExam(examName,stuId,stuName,stuDept,stuClazz,examTime,score,paperId,releaseExamId);
        if(row>0){
            System.out.println("考试记录同步成功");
        }
        System.out.println("错题ID"+wrongAnswer);
        System.out.println("分数：" + score);
        return score;
    }

    @RequestMapping("successSubmit")
    public String getSuccessSubmit(@RequestParam("score") Integer score,Model model){
        model.addAttribute("score",score);
        return "student/exam/JiaoJuanSuccess";
    }
   /* //点击考试界面 进入考试 获取试卷试题 并显示-----后去换生成随机数获取题目
    @RequestMapping(value = "/Examing",method = RequestMethod.GET)
    public String goExaming(Model model,@RequestParam("paperId") int paperId, @RequestParam("number") String number, @RequestParam("score")String score, @RequestParam("stuId")int stuId){
        model.addAttribute("number",number);
        model.addAttribute("score",score);
        model.addAttribute("stuId",stuId);
        model.addAttribute("paperId",paperId);
     *//*   model.addAttribute("majorId",majorId);*//*

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
    }*/

   /* //点击交卷自动判分 实现
    @RequestMapping(value = "/jiaojuan",method = RequestMethod.POST)
    @ResponseBody
    public int jiaojuan(@RequestParam("stuId") int stuid,@RequestParam("paperId") int paperId, @RequestParam("answer1") String answer1,@RequestParam("answer2")String answer2,@RequestParam("answer3")String answer3, @RequestParam("answer4")String answer4,
                        @RequestParam("ranswer1")String  ranswer1, @RequestParam("ranswer2")String ranswer2,@RequestParam("ranswer3")String  ranswer3, @RequestParam("ranswer4")String ranswer4,
                        @RequestParam("score1")int score1, @RequestParam("score2")int score2, @RequestParam("score3")int score3, @RequestParam("score4")int score4){
        int sum=0;
        if(answer1.equals(ranswer1)&&answer1!=null){sum=score1;}
        if(answer2.equals(ranswer2)&&answer2!=null){sum=sum+score2;}
        if(answer3.equals(ranswer3)&&answer3!=null){sum=sum+score3;}
        if(answer4.equals(ranswer4)&&answer4!=null){sum=sum+score4;}
        System.out.println(sum);
        Student student=examService.fingStuById(stuid);
        Paper paper=examService.findPaperById(paperId);
        String examName=paper.getPaperName();
        int stuId=student.getStuid();
        String stuName=student.getStuname();
        String stuDept=student.getDepartName();
        String stuClazz=student.getClazzName();
        String examTime=new Date().toLocaleString();
        int paperid=paper.getPaperId();//后添加
        int examScore=sum;
        //paper.getPaperName(),student.getStuid(),student.getStuname(),student.getDept(),student.getClazz(),new Date().toString(),sum
        //String examName,int stuId,String stuName,String stuDept,String stuClazz,String examTime,int examScore
        int i=examService.StuInsertExam(examName,stuId,stuName,stuDept,stuClazz,examTime,examScore,paperid);
        return i;
    }

    //自动交卷判分
    @RequestMapping(value = "/AutoJiaoJuan",method = RequestMethod.POST)
    @ResponseBody
    public int AutoJiaojuan( @RequestParam("stuId") int stuid,@RequestParam("paperId") int paperId, @RequestParam("answer1") String answer1,@RequestParam("answer2")String answer2,@RequestParam("answer3")String answer3, @RequestParam("answer4")String answer4,
                             @RequestParam("ranswer1")String  ranswer1, @RequestParam("ranswer2")String ranswer2,@RequestParam("ranswer3")String  ranswer3, @RequestParam("ranswer4")String ranswer4,
                             @RequestParam("score1")int score1, @RequestParam("score2")int score2, @RequestParam("score3")int score3, @RequestParam("score4")int score4){
        int sum=0;
        if(answer1.equals(ranswer1)&&answer1!=null){sum=score1;}
        if(answer2.equals(ranswer2)&&answer2!=null){sum=sum+score2;}
        if(answer3.equals(ranswer3)&&answer3!=null){sum=sum+score3;}
        if(answer4.equals(ranswer4)&&answer4!=null){sum=sum+score4;}
        System.out.println(sum);
        Student student=examService.fingStuById(stuid);
        Paper paper=examService.findPaperById(paperId);
        String examName=paper.getPaperName();
        int stuId=student.getStuid();
        String stuName=student.getStuname();
        String stuDept=student.getDepartName();
        String stuClazz=student.getClazzName();
        String examTime=new Date().toLocaleString();
        int paperid=paper.getPaperId();//后添加
        int examScore=sum;
        //paper.getPaperName(),student.getStuid(),student.getStuname(),student.getDept(),student.getClazz(),new Date().toString(),sum
        //String examName,int stuId,String stuName,String stuDept,String stuClazz,String examTime,int examScore
        int i=examService.StuInsertExam(examName,stuId,stuName,stuDept,stuClazz,examTime,examScore,paperid);
        return sum;
    }
*/
    //学生主页 进入考试记录
    @RequestMapping(value = "/ExamHistory",method = RequestMethod.GET)
    public String goHistory(Model model,@RequestParam("stuId") int stuId){
        List<Exam> list=examService.findExamScoreById(stuId);
        model.addAttribute("list",list);
        return "student/history/ExamHistory";
    }

 /*   //判分成功跳转学生主页
    @RequestMapping(value = "/goStudent")
    public String goStudent(@RequestParam("stuId") int stuId, HttpServletRequest request, HttpServletResponse response, HttpSession session){

    Student student=teacherIndexService.getStuByStuId(stuId);
        Integer stuid=student.getStuid();
        String stuname=student.getStuname();
        String stupassword=student.getStupassword();
        String stuphonenumber=student.getStuphonenumber();
        String stuemail=student.getStuemail();
        Integer clazzId=student.getClazzId();
        Integer departId=student.getDepartId();
        Integer stateId=student.getStateId();
        Integer majorId=student.getMajorId();

        request.getSession().setAttribute("stuid",stuid);
        request.getSession().setAttribute("stuname",stuname);
        request.getSession().setAttribute("stupassword",stupassword);
        request.getSession().setAttribute("stuphonenumber",stuphonenumber);
        request.getSession().setAttribute("stuemail",stuemail);
        request.getSession().setAttribute("clazzId",clazzId);
        request.getSession().setAttribute("departId",departId);
        request.getSession().setAttribute("stateId",stateId);
        request.getSession().setAttribute("majorId",majorId);
        return "StudentIndex";
    }*/
 //判分成功跳转学生主页
 @RequestMapping(value = "/goStudent")
 public String goStudent(){
     return "student/index/StudentIndex";
 }
@RequestMapping(value = "/Success")
    public String success(){
        return "student/exam/JiaoJuanSuccess";
}

    //考试详情加载
    @RequestMapping(value = "/getHistory",method = RequestMethod.POST)
    @ResponseBody
    public String history(Model model,@RequestParam("paperId") int paperId,@RequestParam("stuId") int stuId,@RequestParam("examTime") String examTime){
        List<StuAnswer> list=studentFunctionService.getHistory(paperId, stuId);
        model.addAttribute("list",list);
        return "0";
    }

    //考试详情加载访问
    @RequestMapping(value = "/HistoryGo",method = RequestMethod.GET)
    public String Gohistory(Model model,@RequestParam("paperId") int paperId,@RequestParam("stuId") int stuId,@RequestParam("examTime") String examTime){
        //获取单选题目详情
        List<StuAnswer> list=studentFunctionService.getHistory(paperId, stuId);
        model.addAttribute("HistoryList",list);
        model.addAttribute("stuId",stuId);
        model.addAttribute("paperId",paperId);
        model.addAttribute("examTime",examTime);
        //获取多选题目详情
        List<StuAnswer> list2=studentFunctionService.getCheckHistory(paperId, stuId);
        model.addAttribute("CheckHistory",list2);
        return "student/history/historyImpl";
    }

    //个人成绩分析
@RequestMapping(value = "/scoreAna")
    public String ana(){
     return "student/ScoreAnaly/ScoreAnaly";
}

//Echart图
@RequestMapping("getResource")
@ResponseBody
public List<CountModel> getResourceInfo(){
    List<CountVo> countVo=examService.getResource();
    System.out.println(countVo);
    Integer questionNum=countVo.get(0).getQuestionNum();
    Integer releaseNum=countVo.get(0).getReleaseNum();
    Integer papNum=countVo.get(0).getPapNum();
    CountModel countModel=new CountModel("不及格",questionNum);
    CountModel countModel2=new CountModel("良好",papNum);
    CountModel countModel3=new CountModel("优秀",releaseNum);
    List<CountModel> countModels=new ArrayList<>();
    countModels.add(countModel);
    countModels.add(countModel2);
    countModels.add(countModel3);
    return  countModels;
}

//学习论坛进入
    @RequestMapping(value = "/study")
    public String study(@RequestParam("stuId") int stuId,Model model){
   List<StudyLocation> list=studyService.getallList();
   model.addAttribute("list",list);
   int a =list.size();
   model.addAttribute("count",a);
   List<Course> courseList=questionService.getCourse();
   model.addAttribute("courseList",courseList);
     return "student/StudyLocation";
    }


//分享论坛
    @RequestMapping(value = "/toShare",method = RequestMethod.POST)
    @ResponseBody
    public String share(@RequestParam("stuId") int stuId,@RequestParam("questionId")int questionId,Model model){
    String time=new Date().toLocaleString();
    int i=studyService.insertShare(stuId, questionId,time);
    return "q1";
}



}