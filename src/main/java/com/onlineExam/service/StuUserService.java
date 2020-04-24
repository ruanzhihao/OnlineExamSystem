package com.onlineExam.service;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuUserService {
    //移除错题
    public  boolean removeError(Integer questionId);
    //查看错题
    public List<StuAnswer> getErrorQuestion(Integer stuId);
    public Student getStuByEmail(String stuemail);
    public String login(@Param("username") String username, @Param("password") String password);

    //登录验证
    public LoginUser findByUsername(String username);
    public boolean findReisterUsername(String username);
    //注册用户账号
    public boolean register(LoginUser user);
    //注册学生用户账号信息存储
    public boolean addStuInformation(Student user);
    public boolean updatePassword(LoginUser user);
    public boolean updateStuPassword(Student student);
    //查询用户信息
    public Student findInformationByUsername(String username);
    //修改用户信息
    public  boolean updateInformation(Student user);

    List<Student> getStudentList();//获取所有的学生信息id
    int addStudent(Student student);//添加学生
    int deleteStudent(Integer id);//删除学生
    int updateStudent(Student student);//修改学生信息
    Student findStudentById(Integer id);//通过id查找
    List<Student> findStudentByclazzId(Integer clazzId);//根据班级查找
    List<Exam> findScoreBystuname(String stuname);//根据姓名查找
    List<Exam> findExamByexamName(String examName,String stuname);
    List<Student> queryStudent(String stuname);////根据学生姓名查询
    List<Student> queryStudent1(String stuname);////根据学生姓名查询
    List<Clazz> getClazzList();//获取班级列表
    List<Major> getMajorList();//获取专业列表
    List<Depart> getDepartList();//获取学院列表
    List<State> getStateList();//获取状态
    List<Exam> getScoreBystuname(String stuname);
}
