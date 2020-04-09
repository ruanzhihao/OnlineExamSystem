package com.onlineExam.service;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StuUserService {

    public String login(@Param("username") String username, @Param("password") String password);

    //登录验证
    public LoginUser findByUsername(String username);
    public boolean findReisterUsername(String username);
    //注册用户账号
    public boolean register(LoginUser user);
    //注册学生用户账号信息存储
    public boolean addStuInformation(Student user);
    public boolean updatePassword(LoginUser user);
    //查询用户信息
    public Student findInformationByUsername(String username);
    //修改用户信息
    public  boolean updateInformation(Student user);
}
