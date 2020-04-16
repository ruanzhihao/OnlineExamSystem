package com.onlineExam.service;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuUserService {

    public Student getStuByEmail(String email);
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

    List<Student1> getStudentList();//获取所有的学生信息id
    int addStudent(Student1 student1);//添加学生
    int deleteStudent(Integer id);//删除学生
    int updateStudent(Student1 student1);//修改学生信息
    Student1 findStudentById(Integer id);//通过id查找
    List<Student1> queryStudent(String stuname);////根据学生姓名查询
    List<Clazz> getClazzList();//获取班级列表
    List<Major> getMajorList();//获取专业列表
    List<Depart> getDepartList();//获取学院列表
    List<State> getStateList();//获取状态
}
