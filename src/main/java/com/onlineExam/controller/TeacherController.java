package com.onlineExam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.domain.*;
import com.onlineExam.mapper.MessageMapper;
import com.onlineExam.mapper.TeacherMapper;
import com.onlineExam.modules.common.controller.MainController;
import com.onlineExam.modules.util.SendMail;
import com.onlineExam.service.ReleaseExamService;
import com.onlineExam.service.StuUserService;
import com.onlineExam.service.TeacherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private StuUserService stuUserService;
    @Autowired
    private MessageMapper messageMapper;
   @Autowired
   private ReleaseExamService releaseExamService;

    //登录
    @RequestMapping(value="/TeaLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request,HttpServletResponse response, HttpSession session, @RequestParam("validateCode")String validateCode) {

        MainController mainController = new MainController();
        Map map = mainController.checkLoginValidateCode(request, validateCode);
        String flag = map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");

        Teacher teacher=teacherMapper.findTeaByUsername(username);
        Integer teacherid=teacher.getTeacherid();
        String teachername=teacher.getTeachername();
        String teacherphoneNumber=teacher.getTeacherphoneNumber();
        String teacheremail=teacher.getTeacheremail();
        Integer majorId=teacher.getMajorId();
        Integer departId=teacher.getDepartId();
        Integer clazzId=teacher.getClazzId();


        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("teacherid",teacherid);
        request.getSession().setAttribute("teachername",teachername);
        request.getSession().setAttribute("teacherphoneNumber",teacherphoneNumber);
        request.getSession().setAttribute("teacheremail",teacheremail);
        request.getSession().setAttribute("majorId",majorId);
        request.getSession().setAttribute("departId",departId);
        request.getSession().setAttribute("clazzId",clazzId);

        Md5Hash md5registepassword=new Md5Hash( password, username,5);
        String md5password=md5registepassword.toString();
        System.out.println("账号："+username+"密码："+password+"    加密后："+md5password);

        String roles=teacherMapper.login(username,md5password);
        session.setAttribute("roles", roles);
        if (roles != null&&flag.equals("true") ) {
            if(request.getParameter("rememberMe_tea")!=null){

                addCookie(username,password,response,request);
            }
            return "TeacherManagerIndex";
        } else {
            return "index";
        }
    }
    public static void addCookie(String username, String password, HttpServletResponse response, HttpServletRequest request)  {
        //创建cookie
        Cookie nameCookie = new Cookie(username, password);
        nameCookie.setPath("/");//设置cookie路径
        //设置cookie保存的时间 单位：秒
        nameCookie.setMaxAge(60*60*24);
        //将cookie添加到响应
        response.addCookie(nameCookie);
    }

    @ResponseBody
    @RequestMapping(value = "/getTeaCookie",method = RequestMethod.POST)
    public Map<String,String> initCookie(String username, HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        Map<String,String> map=new HashMap<>();
        for (Cookie c:cookies){
            if(c.getName().equals(username)){
                String password=c.getValue();
                map.put("username",username);
                map.put("password",password);
                return map;
            }
        }
        return null;
    }

    //找回密码
    @RequestMapping("/retrieveTeaPwd")
    public String toGetCode(){
        return "retrieveTeaPwd";
    }

    //找回密码控制器
    @RequestMapping(value = "/getTeaCode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
    @ResponseBody // 此注解不能省略 否则ajax无法接受返回值
    public String retrievePassword(HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Map<String, Object> map = new HashMap<String, Object>();
        //获取用户的邮箱
        String email = request.getParameter("email");

        //实例化一个发送邮件的对象
        SendMail mySendMail = new SendMail();
        //根据邮箱找到该用户信息
        Teacher teacher=teacherMapper.getTeaByEmail(email);
        String username=teacher.getUsername();
        LoginUser user=teacherMapper.findRegisterUsername(username);
        //String password=user.getPassword();
        //修改密码并返回
        //产生随机的6位数密码
        String Password = ((int)((Math.random()*9+1)*100000))+"";
        //修改密码成功后进行发送邮件
        //设置收件人和消息内容
        mySendMail.sendMail(email, "在线考试平台提醒您：您的新密码为："+ Password);

        Md5Hash md5password = new Md5Hash(Password, username, 5);
        String userpassword = md5password.toString();
        user.setUsername(username);
        user.setPassword(userpassword);
        teacher.setTeacherpassword(userpassword);
        //将加密后的新密码保存到数据库
        teacherMapper.updatePassword(user);
        teacherMapper.updateTeaPassword(teacher);


        System.out.println("重置密码：数据库中密码为："+userpassword);

        map.put("code", 200);
        map.put("msg", "恭喜，找回密码成功，请前往邮箱查看密码！");
        JSONObject jsonFail = new JSONObject(map);
        return jsonFail.toString();
    }
    //教师端个人信息页面
    @RequestMapping("/teaPersoninfo")
    public String teaPersoninfo(){
        return "teaPersoninfo";
    }
    //教师端修改密码
    @RequestMapping("/changeTeaPwd")
    public String changeTeaPwd(){
        return "changeTeapwd";
    }

    //修改教师个人信息
    @RequestMapping(value = "/ChangeTeaInfo",method = RequestMethod.POST)
    public String ChangeTeaInfo(HttpServletRequest request,HttpSession session,Model model,String teachername,Integer clazzId,
                                Integer majorId,Integer departId,String teacherphoneNumber,String teacheremail){

        String username=(String)request.getSession().getAttribute("username");
        Teacher teacher=teacherService.findTeaByUsername(username);
        teacher.setUsername(username);
        teacher.setTeachername(teachername);
        teacher.setTeacherphoneNumber(teacherphoneNumber);
        teacher.setTeacheremail(teacheremail);
        teacher.setClazzId(clazzId);
        teacher.setDepartId(departId);
        teacher.setMajorId(majorId);
        boolean changeTeaInfo= teacherService.updateInformation(teacher);
        if(changeTeaInfo){
            model.addAttribute("msg","修改成功");
        }else {
            model.addAttribute("msg","修改失败");
        }
        return "teaPersoninfo";

    }
    //修改教师密码
    @RequestMapping(value = "/ChangeTeaPwd",method = RequestMethod.POST)
    public String changeTeaPwd(String password1, String password2, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        Md5Hash md5password = new Md5Hash(password1, username, 5);
        String userpassword = md5password.toString();   //加密后的原密码
        LoginUser user=teacherService.findByUsername(username);
        LoginUser user2 = new LoginUser();
        if (userpassword.equals(user.getPassword())) {
            Md5Hash md5password2 = new Md5Hash(password2, username, 5);  //新密码加密后
            user2.setUsername(username);
            user2.setPassword(md5password2.toString());

            teacherService.updatePassword(user2);
            Teacher teacher=new Teacher();
            teacher.setUsername(username);
            teacher.setTeacherpassword(md5password2.toString());
            teacherService.updateTeaPassword(teacher);
            System.out.println("修改密码后数据库中加密密码："+user2.getPassword()+"     teacher表中："+md5password2.toString());
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "changeTeapwd";
    }
    //消息通知
    @RequestMapping("/teaMessage")
    public String findMessage(Model model){
        List<Message> messageList= messageMapper.findMessage();

        model.addAttribute("messageList",messageList);
        return "teaMessage";
    }
    @RequestMapping("/tearead")
    @Transactional
    public String updateTeaRead(Long id){
        int result= messageMapper.updateTeaRead(id);
        return "teaMessage";
    }
    @RequestMapping("/teaReadAll")
    public String teaReadAll(){
        int rs=messageMapper.teaReadAll();
        return "teaMessage";
    }



    //进入教师端
    @RequestMapping("/TeacherIndex")
    public String Login(){
        return "TeacherManagerIndex";
    }

    //学生管理
    @RequestMapping(value = "/findStudentByclazzId",method = RequestMethod.GET)
    public String findStudentByClazzId(HttpSession session,Model model){
        Integer clazzId=(int)session.getAttribute("clazzId");
        List<Student> studentList= stuUserService.findStudentByclazzId(clazzId);
        model.addAttribute("studentList",studentList);
        return "Teacher-StudentManager";
    }

    //学生查找
    @RequestMapping(value = "/queryStudent1",method = RequestMethod.GET)
    @ResponseBody
    public String queryStudent(@RequestParam("stuname") String stuname){
        List<Student> list=stuUserService.queryStudent(stuname);
        if (list ==null){
            return "查找失败";
        }else{
            return "查找成功";
        }
    }
    //查找后的显示信息
    @RequestMapping(value = "/queryShowStudent1",method = RequestMethod.GET)
    public String queryShow_s(@RequestParam("stuname") String stuname,Model model){
        List<Student> studentList=stuUserService.queryStudent(stuname);
        model.addAttribute("studentList",studentList);
        return "Teacher-StudentManager";
    }
    //查看学生的成绩
    @RequestMapping(value = "/show",method = RequestMethod.GET)
    public String show(String stuname,Model model){
        List<Exam> examList=stuUserService.getScoreBystuname(stuname);
        model.addAttribute("examList",examList);
        return "Student_score";
    }

    @RequestMapping("/teacherHomePage")
    public String teacherHomePage(){
        return "teacherHomePage";
    }

    @RequestMapping(value = "/showquestion")
    public String showquestion(@RequestParam("stuId") int stuId,@RequestParam("paperId") int paperId,@RequestParam("releaseExamId")int releaseExamId,Model model){
        List<StuAnswer> stuAnswerList=teacherMapper.getQuestion(stuId,paperId,releaseExamId);
        model.addAttribute("stuAnswerList",stuAnswerList);
        return "QuestionBypersion";
    }
    @RequestMapping("TeacherreleaseInfoCenter")
    public String releaseInfo(Model model){
        List<ExamState> states=releaseExamService.getAllState();
        model.addAttribute("states",states);
        return "teacher/release/TeacherReleaseExamInfo";
    }
    @RequestMapping("TeacherreleaseInfo")
    @ResponseBody
    public Page releaseInfo(@RequestParam(value = "page",defaultValue = "1") Integer page,HttpSession session){
        int limit=10;
        Integer teacherid=(Integer)session.getAttribute("teacherid");
        List<ReleaseExam> releaseExams=releaseExamService.getAllReleaseInfoByTeacherId(teacherid);
        PageHelper.startPage(page,limit);
        PageInfo pageInfo=new PageInfo(releaseExams,limit);
        Page pages = Page.success(pageInfo.getTotal(), pageInfo.getList());
        return pages;
    }
}
