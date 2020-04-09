package com.onlineExam.mapper;

import com.onlineExam.domain.LoginUser;
import com.onlineExam.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

//@Mapper
@Component
public interface StuUserMapper {

    public String login(@Param("username") String username, @Param("password") String password);
    //登录
    public LoginUser findByUsername(String username);

    //判断用户名是否注册过
    public LoginUser findRegisterUsername(String username);
    //注册用户账号
    public int register(LoginUser user);
    //注册学生用户账号信息存储
    public int addStuInformation(Student user);
   //修改用户密码
    public int updatePassword(LoginUser user);
    //查询用户信息
    public Student findInformationByUsername(String username);
    //修改用户信息
    public int updateInformation(Student user);
}