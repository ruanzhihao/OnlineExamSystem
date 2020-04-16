package com.onlineExam.controller.student;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Student;
import com.onlineExam.mapper.StuUserMapper;
import com.onlineExam.modules.util.SendMail;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StuResetPwdController {
    @Autowired
    private StuUserMapper stuUserMapper;

    @RequestMapping("/retrievePwd")
    public String toGetCode(){
        return "student/retrievePwd";
    }

    //找回密码控制器
    @RequestMapping(value = "/getCode", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
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
        Student student = stuUserMapper.getStuByEmail(email);
        String username=student.getUsername();
        LoginUser user=stuUserMapper.findByUsername(username);
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
        //将加密后的新密码保存到数据库
        stuUserMapper.updatePassword(user);


        System.out.println("重置密码：数据库中密码为："+userpassword);

        map.put("code", 200);
        map.put("msg", "恭喜，找回密码成功，请前往邮箱查看密码！");
        JSONObject jsonFail = new JSONObject(map);
        return jsonFail.toString();
    }


}
