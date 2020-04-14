package com.onlineExam.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.domain.*;
import com.onlineExam.mapper.AdminMapper;
import com.onlineExam.modules.common.controller.MainController;
import com.onlineExam.service.StuUserService;
import com.onlineExam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StuUserService stuUserService;

    @RequestMapping(value="/adminLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam("validateCode")String validateCode){

        MainController mainController=new MainController();
        Map map=mainController.checkLoginValidateCode(request,validateCode);
        String flag=map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println("账号："+username+"密码："+password);

        String roles=adminMapper.login(username,password);
        //System.out.println(roles);
        session.setAttribute("roles",roles);
        if (roles!=null&&flag.equals("true")){
            if(request.getParameter("rememberMe_admin")!=null){
                addCookie(username,password,response,request);
            }
            return "ManagerIndex";

        }else{
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
    @RequestMapping(value = "/getAdminCookie",method = RequestMethod.POST)
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




    //进去教师管理界面
    @RequestMapping(value = "/teenager")
    public String getTeacherList(Model model){
        List<Teacher> list=teacherService.getAllTeacher();
        model.addAttribute("list",list);
        return "TeacherManagement";
    }


    //添加老师
    @RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
    @ResponseBody
    public String addTeacher(@RequestBody Teacher teacher){
        //teacher.setTeacherpassword(MD5Util.MD5EncodeUtf8(teacher.getTeacherpassword()));
        int a=teacherService.addTeacher(teacher);
        return "TeacherManagement";
    }

    //删除教师信息
    @RequestMapping("/deleteTeacher")
    @ResponseBody
    public String deleteTeacher(Integer teacherid){
        int s=teacherService.deleteTeacher(teacherid);
        return "TeacherManagement";
    }

    //修改教师信息
    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
    public String updateTeacher(Teacher teacher){
        //teacher.setTeacherpassword(MD5Util.MD5EncodeUtf8(teacher.getTeacherpassword()));
        int t=teacherService.updateTeacher(teacher);
        return "redirect:/teenager";
    }

    //根据id查找学生
    @RequestMapping("/findTeacherById")
    public String findTeacherById(Integer teacherid, HttpSession session){
        Teacher t=teacherService.findTeacherById(teacherid);
        session.setAttribute("t",t);
        return "Teacher_edit";
    }
    //教师查找
    @RequestMapping(value = "/queryTeacher",method = RequestMethod.GET)
    @ResponseBody
    public String queryTeacher(@RequestParam("teachername") String teachername){
        List<Teacher> list=teacherService.queryTeacher(teachername);
        if (list ==null){
            return "查找失败";
        }else{
            return "查找成功";
        }
    }

    //查找后的显示信息
    @RequestMapping(value = "/queryShowTeacher",method = RequestMethod.GET)
    public String queryShow(@RequestParam("teachername") String teachername,Model model){
        List<Teacher> list=teacherService.queryTeacher(teachername);
        model.addAttribute("list",list);
        return "TeacherManagement";
    }

    //进入学生信息管理界面
    @RequestMapping(value = "/strange")
    public String getStudentList(Model model){
        List<Student1> studentList=stuUserService.getStudentList();
        model.addAttribute("studentList",studentList);
        return "StudentManagement";
    }
    //添加学生
    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody Student1 student1){
        int a=stuUserService.addStudent(student1);
        return "StudentManagement";
    }
    //删除学生信息
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(Integer id){
        int s=stuUserService.deleteStudent(id);
        return "TeacherManagement";
    }
    //修改学生信息
    @RequestMapping(value = "updateStudent",method = RequestMethod.POST)
    public String updateStudent(Student1 student1){
        int s=stuUserService.updateStudent(student1);
        return "redirect:/strange";
    }
    //根据id查找学生
    @RequestMapping("/findStudentById")
    public String findStudentById(Integer id,HttpSession session){
        Student1 s=stuUserService.findStudentById(id);
        session.setAttribute("s",s);
        return "Student_edit";
    }
    //学生查找
    @RequestMapping(value = "/queryStudent",method = RequestMethod.GET)
    @ResponseBody
    public String queryStudent(@RequestParam("stuname") String stuname){
        List<Student1> list=stuUserService.queryStudent(stuname);
        if (list ==null){
            return "查找失败";
        }else{
            return "查找成功";
        }
    }
    //查找后的显示信息
    @RequestMapping(value = "/queryShowStudent",method = RequestMethod.GET)
    public String queryShow_s(@RequestParam("stuname") String stuname,Model model){
        List<Student1> studentList=stuUserService.queryStudent(stuname);
        model.addAttribute("studentList",studentList);
        return "StudentManagement";
    }
    //导出为Excel
    @RequestMapping(value = "/exportstudentlist", method = RequestMethod.POST)
    @ResponseBody
    public List<Student1> exportStudent(){
        List<Student1> studentList1 = stuUserService.getStudentList();
        return studentList1;
    }

    @RequestMapping(value = "/queryClazz")
    @ResponseBody
    public List getClazzList(){
        List<Clazz> c_list=stuUserService.getClazzList();
        return c_list;
    }
    @RequestMapping(value = "/queryMajor")
    @ResponseBody
    public List getMajorList(){
        List<Major> m_list=stuUserService.getMajorList();
        return m_list;
    }
    @RequestMapping(value = "/queryDepart")
    @ResponseBody
    public List getDepartList(){
        List<Depart> d_list=stuUserService.getDepartList();
        return d_list;
    }
    @RequestMapping(value = "/queryState")
    @ResponseBody
    public List getStateList(){
        List<State> s_list=stuUserService.getStateList();
        return s_list;
    }

}
