package com.onlineExam.mapper;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Mapper
@Component
public interface StuUserMapper {
    public int insertUrl(@Param("headerurl") String headerurl,@Param("username") String username);
    //移除错题
    public int removeError(Integer questionId);
    //查看错题
    public List<StuAnswer> getErrorQuestion(Integer stuId);
    public Student getStuByEmail(String stuemail);

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
    //修改学生密码
    public int updateStuPassword(Student  student);
    //查询用户信息
    public Student findInformationByUsername(String username);
    //修改用户信息
    public int updateInformation(Student user);

    List<Student> getStudentList();//获取所有的学生信息
    int addStudent(Student student);//添加学生
    int deleteStudent(Integer id);//删除学生
    int updateStudent(Student student);//修改学生信息
    Student findStudentById(Integer id);//通过id查找
    List<Student> findStudentByclazzId(@Param("clazzId") Integer clazzId);//根据班级查询
    List<Exam> findScoreBystuname(@Param("stuname") String stuname);//根据姓名查找考试
    List<Exam> findExamByexamName(@Param("examName") String examName,@Param("stuname") String stuname);
    List<Student> queryStudent(@Param("stuname") String stuname);////根据学生姓名查询
    List<Student> queryStudent1(@Param("stuname") String stuname);////根据学生姓名查询
    List<Clazz> getClazzList();//获取班级列表
    List<Major> getMajorList();//获取专业列表
    List<Depart> getDepartList();//获取学院列表
    List<State> getStateList();//获取状态
    List<Exam> getScoreBystuname(String stuname);
}