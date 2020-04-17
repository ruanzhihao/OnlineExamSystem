package com.onlineExam.controller.student;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Student;
import com.onlineExam.service.StuUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "学生注册接口")
@Controller
//@RequestMapping("/student")
public class StuRegisterController {
    //属性注入
    @Autowired
    private StuUserService userService;
    /**
     * 跳转页面
     */
    @RequestMapping("/sturegister")
    public String register(){
        return "student/sturegister";
    }

    //判断注册的用户名是否存在
    @ApiOperation(value = "判断注册的用户名是否存在")
    @RequestMapping("/findReisterUsername")
    @ResponseBody
    public Map<String, Object> findRegisterUsername(String username){
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        boolean isExist;
        if(userService.findReisterUsername(username)){
            isExist=true;
        }
        else{
            isExist=false;
        }
        resultMap.put("isExist", isExist);
       // return "sturegister";
        return resultMap;
    }
    /**
     * 注册逻辑处理
     */
    @ApiOperation(value = "学生用户注册")
    @RequestMapping("/StuRegister")
    public String toregister(String username, String password, Model model,HttpServletRequest request,
    Integer stuid,String stuname, String phone, String email,Integer clazz,Integer dept,Integer major)
    {
        if(userService.findReisterUsername(username)){
            model.addAttribute("msg", "用户名已经存在");
            return "student/sturegister";
        }
        //进行Md5加密，第一个参数为密码，第二参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5registepassword=new Md5Hash( password, username,5);
        System.out.println(md5registepassword.toString());
        LoginUser user=new LoginUser();
        user.setUsername(username);
        user.setRoles("student");
        user.setPassword(md5registepassword.toString());

        //注册用户账号
        boolean addregister= userService.register(user);
        //注册用户详细信息
        Student stuInformation=new Student();
        stuInformation.setUsername(username);
        stuInformation.setStupassword(md5registepassword.toString());
        stuInformation.setStuid(stuid);
        stuInformation.setStuname(stuname);
        stuInformation.setStuphonenumber(phone);
        stuInformation.setStuemail(email);
        stuInformation.setClazzId(clazz);
        stuInformation.setDepartId(dept);
        stuInformation.setMajorId(major);
        boolean addStuInformation= userService.addStuInformation(stuInformation);
        if(addregister&&addStuInformation) {
            model.addAttribute("msg", "注册成功");
        }
        else{
            model.addAttribute("msg", "注册失败");
        }
        return "student/sturegister";
    }

}
