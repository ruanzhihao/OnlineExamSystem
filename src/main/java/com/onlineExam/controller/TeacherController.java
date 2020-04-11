package com.onlineExam.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.onlineExam.domain.Teacher;
import com.onlineExam.mapper.TeacherMapper;
import com.onlineExam.modules.common.controller.MainController;
import com.onlineExam.service.TeacherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    //登录
    @RequestMapping(value="/TeaLogin",method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, HttpSession session, @RequestParam("validateCode")String validateCode) {

        MainController mainController = new MainController();
        Map map = mainController.checkLoginValidateCode(request, validateCode);
        String flag = map.get("status").toString();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
        Md5Hash md5registepassword=new Md5Hash( password, username,5);
        String md5password=md5registepassword.toString();
        System.out.println("账号："+username+"密码："+password);

        String roles=teacherMapper.login(username,md5password);
        session.setAttribute("roles", roles);
        if (roles != null&&flag.equals("true") ) {
            return "TeacherManagement";
        } else {
            return "index";
        }
    }
}
