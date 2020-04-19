package com.onlineExam.controller;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Teacher;
import com.onlineExam.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "教师注册接口")
@Controller
//@RequestMapping("/teacher")
public class TeaRegisterController {
    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teacherRegister")
    public String register(){
        return "teacherregister";
        //return   "student/sturegister";

    }

    /*//判断注册的用户名是否存在
    @ApiOperation(value = "判断注册的用户名是否存在")
    @RequestMapping("/findReisterUsername")
    @ResponseBody
    public Map<String, Object> findRegisterUsername(String username){
        Map<String, Object> resultMap =  new HashMap<String,Object>();
        int i;
        boolean isExist;
        if(teacherService.findReisterUsername(username)){
            isExist=true;
        }
        else{
            isExist=false;
        }
        resultMap.put("isExist", isExist);
        return resultMap;
    }
*/
    @ApiOperation(value = "教师用户注册")
    @RequestMapping("/TeaRegister")
    public String toregister(String username, String teachername,String teacherpassword, Model model,
                             String teacherphoneNumber,String teacheremail,Integer majorId,Integer departId,Integer clazzId)
    {
        if(teacherService.findReisterUsername(username)){
            model.addAttribute("msg", "用户名已经存在");
            return "teacherregister";
        }
        //进行Md5加密，第一个参数为密码，第二参数是以用户名为盐，第三个参数是加密的次数
        Md5Hash md5registepassword=new Md5Hash( teacherpassword, username,5);
        System.out.println(md5registepassword.toString());
        LoginUser user=new LoginUser();
        user.setUsername(username);
        user.setRoles("teacher");
        user.setPassword(md5registepassword.toString());

        //注册用户账号
        boolean addregister= teacherService.register(user);
        //注册用户详细信息
        Teacher teacher=new Teacher();
        teacher.setUsername(username);
        teacher.setTeachername(teachername);
        teacher.setTeacherpassword(md5registepassword.toString());
        teacher.setTeacherphoneNumber(teacherphoneNumber);
        teacher.setTeacheremail(teacheremail);
        teacher.setMajorId(majorId);
        teacher.setDepartId(departId);
        teacher.setClazzId(clazzId);
        teacher.setStateId(1);

        boolean addTeacher= teacherService.addTeacherInfo(teacher);
        if(addregister&&addTeacher) {
            model.addAttribute("msg", "注册成功");
        }
        else{
            model.addAttribute("msg", "注册失败");
        }
        return "teacherregister";
    }

}
