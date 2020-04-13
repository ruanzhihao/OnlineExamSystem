package com.onlineExam.controller.student;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Student;
import com.onlineExam.service.StuUserService;
import io.swagger.annotations.Api;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "学生信息接口")
@Controller
//@RequestMapping("/student")
public class StuChangeController {
    //属性注入
    @Autowired
    private StuUserService stuUserService;

    //    //修改用户的信息
    @RequestMapping(value = "/ChangeStuInfo" ,method=RequestMethod.POST)
    public String ChangeInformation(HttpServletRequest request, HttpSession session, Model model, String stuid,
            String stuname, String sex, String clazz, String phone,String major,String dept,String motto ) throws Exception {
        String username=(String)request.getSession().getAttribute("username");

        Student stuInformation=new Student();
        stuInformation.setUsername(username);
        stuInformation.setSex(sex);
        stuInformation.setStuname(stuname);
        stuInformation.setStuid(stuid);
        stuInformation.setClazz(clazz);
        stuInformation.setPhone(phone);
        stuInformation.setMajor(major);
        stuInformation.setDept(dept);
        stuInformation.setMotto(motto);

        boolean changeInformation= stuUserService.updateInformation(stuInformation);

        if(changeInformation){
            model.addAttribute("msg", "修改成功");
        }
        else {
            model.addAttribute("msg", "修改失败");
        }
        //return "student/changeInformation";
        return "personInfo";
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
            System.out.println("修改密码后数据库中加密密码："+user2.getPassword());
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "changepwd";
    }


}
