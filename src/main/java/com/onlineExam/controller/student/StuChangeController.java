package com.onlineExam.controller.student;

import com.onlineExam.domain.*;
import com.onlineExam.service.MyCollectionService;
import com.onlineExam.service.QuestionService;
import com.onlineExam.service.StuUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Api(tags = "学生信息接口")
@Controller
//@RequestMapping("/student")
public class StuChangeController {
    //属性注入
    @Autowired
    private StuUserService stuUserService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private MyCollectionService myCollectionService;

    @RequestMapping("/stupersonal")
    public String stuPersonal(){
       return "student/personInfo";
    }
    @RequestMapping("/updateStuPass")
    public String updatePass(){
        return "student/changepwd";
    }

    @RequestMapping("/kTouch")
    public String kTouch(){
        return "student/kTouch";
    }
    //    //修改用户的信息
    @RequestMapping(value = "/ChangeStuInfo" ,method=RequestMethod.POST)
    public String ChangeInformation(HttpServletRequest request, HttpSession session, Model model,
             Integer clazz, String phone,Integer major,Integer dept ,String email) throws Exception {
        String username=(String)request.getSession().getAttribute("username");

        Student stuInformation=new Student();
        stuInformation.setUsername(username);
        stuInformation.setClazzId(clazz);
        stuInformation.setStuphonenumber(phone);
        stuInformation.setMajorId(major);
        stuInformation.setDepartId(dept);
        stuInformation.setStuemail(email);

        boolean changeInformation= stuUserService.updateInformation(stuInformation);

        if(changeInformation){
            model.addAttribute("msg", "修改成功");
        }
        else {
            model.addAttribute("msg", "修改失败");
        }
        //return "student/changeInformation";
        return "student/personInfo";
    }

    //修改密码，先加密认证原密码，再对加密新密码进行修改
    @RequestMapping("/ChangeStuPwd")
    public String ChangePassword(String password1, String password2, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Md5Hash md5password = new Md5Hash(password1, username, 5);
        String userpassword = md5password.toString();   //加密后的原密码
        //System.out.println("执行修改密码操作：-------加密后的原密码:"+userpassword);
        LoginUser user = stuUserService.findByUsername(username);

        LoginUser user2 = new LoginUser();
        if (userpassword.equals(user.getPassword())) {
            Md5Hash md5password2 = new Md5Hash(password2, username, 5);  //新密码加密后
            user2.setUsername(username);
            user2.setPassword(md5password2.toString());
            //System.out.println("执行修改密码操作：------新密码："+password2+"   ;新密码加密后:"+md5password2.toString());

            stuUserService.updatePassword(user2);
            Student student=new Student();
            student.setUsername(username);
            student.setStupassword(md5password2.toString());
            stuUserService.updateStuPassword(student);
            System.out.println("修改密码后数据库中加密密码："+user2.getPassword());
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "student/changepwd";
    }
    //退出登录
    @RequestMapping(value = "/logout",method = {RequestMethod.POST,RequestMethod.GET})
    public String logout(HttpServletRequest request){

        Enumeration em=request.getSession().getAttributeNames();
        while (em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        return "index";
    }

    //错题本
    @RequestMapping("/myerror")
    public String myError(HttpSession session,Model model){
        Integer stuId=(Integer)session.getAttribute("stuid");
        List<StuAnswer> stuAnswerList= stuUserService.getErrorQuestion(stuId); //根据学号查找该学生答题情况
        model.addAttribute("stuAnswerList",stuAnswerList);
        return "student/errorQuestion";
    }
    @RequestMapping("/removeErrors")
    public String removeError(Integer questionId){
        boolean flag= stuUserService.removeError(questionId);
        return "student/errorQuestion";
    }

    // 我的收藏
    @GetMapping(value = "/collection")
    public String getIndex(HttpSession session, Model model) {
        Integer stuId = (Integer) session.getAttribute("stuid");
        List<MyCollection> myCollections = myCollectionService.getList(stuId);
        model.addAttribute("myCollections", myCollections);
        return "student/myCollection";
    }

    @GetMapping(value = "/collection/add")
    @ResponseBody
    public ResponseData add(HttpSession session,
                            @RequestParam(value = "questionId") Integer questionId) {
        Integer stuId = (Integer) session.getAttribute("stuid");
        MyCollection myCollection =
                MyCollection.builder().questionid(questionId).userid(stuId).createtime(new Date()).build();
        myCollectionService.add(myCollection);
        return ResponseData.success();
    }

    @GetMapping(value = "/collection/del")
    @ResponseBody
    public ResponseData del(@RequestParam(value = "id") Integer id) {
        myCollectionService.del(id);
        return ResponseData.success();
    }

}
