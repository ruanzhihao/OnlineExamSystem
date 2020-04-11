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

import javax.servlet.http.HttpSession;

@Api(tags = "学生信息接口")
@Controller
//@RequestMapping("/student")
public class StuChangeController {
    //属性注入
    @Autowired
    private StuUserService userService;

    //跳转到修改密码界面
    @RequestMapping("/toChangepassword")
    public String toChangepassword() {
        return "student/Changepassword";
    }

    @RequestMapping("/toChangeInformation")
    public String toChangeInformation() {
        return "student/changeInformation";
    }


    @RequestMapping(value = "/ChangeStuInformation",method = RequestMethod.POST)
    public String ChangeStuInfomation(Student student, HttpSession session) {
        String username=(String)session.getAttribute("username");
        Student stu=userService.findInformationByUsername(username);
       boolean flag= userService.updateInformation(student);
        return "personInfo";

    }

    //修改密码，先加密认证原密码，再加密新密码进行修改
    @RequestMapping("/ChangePassword")
    public String ChangePassword(String password1, String password2, Model model, HttpSession session)  {
        String username = (String) session.getAttribute("username");
        Md5Hash md5password = new Md5Hash(password1, username, 5);
        String userpassword = md5password.toString();
//       System.out.println(userpassword);
        LoginUser user = userService.findByUsername(username);
        LoginUser user2 = new LoginUser();
        if (userpassword.equals(user.getPassword())) {
            Md5Hash md5password2 = new Md5Hash(password2, username, 5);
            user2.setUsername(username);
            user2.setPassword(md5password2.toString());
            userService.updatePassword(user2);
            model.addAttribute("msg", "修改成功");
        } else {
            model.addAttribute("msg", "原密码错误");
        }
        return "student/changepassword";
    }

    /*//返回学生信息，返回数据为json数据
    @ApiOperation(value = "学生信息")
    @RequestMapping(value = "/ChangeInformation",method = RequestMethod.POST)
    @ResponseBody  // 用于转换对象为json
    public List<Map<String, Object>> list(HttpSession session) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> m = new HashMap<String, Object>();
        String username = (String) session.getAttribute("username");
        Student stuInformation = userService.findInformationByUsername(username);
        m.put("stuid", stuInformation.getStuid());
        m.put("stuname", stuInformation.getStuname());
        m.put("sex", stuInformation.getSex());
        list.add(m);
        return list;
    }

    //    //修改用户的信息
    @RequestMapping(value = "/ChangeStuInformation" ,method=RequestMethod.POST)
    public String ChangeInformation(HttpSession session, Model model,String stuid, String stuname, String sex,String clazz,String phone ) throws Exception {
        String username = (String) session.getAttribute("username");
        //String stuid = (String) session.getAttribute("stuid");
        Student stuInformation=new Student();
        stuInformation.setUsername(username);
        stuInformation.setSex(sex);
        stuInformation.setStuname(stuname);
        stuInformation.setStuid(stuid);
        stuInformation.setClazz(clazz);
        stuInformation.setPhone(phone);
        boolean changeInformation= userService.updateInformation(stuInformation);
        if(changeInformation){
            model.addAttribute("msg", "修改成功");
        }
        else {
            model.addAttribute("msg", "修改失败");
        }
        //return "student/changeInformation";
        return "personInfo";
    }*/
}
