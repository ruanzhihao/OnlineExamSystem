package com.onlineExam.service;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TeacherService {
    public List<Teacher> getAllTeacher();//获取所有的老师信息
    public int addTeacher(Teacher teacher);//添加教师信息
    public int deleteTeacher(Integer teacherid);
    public int updateTeacher(Teacher teacher);
    public Teacher findTeacherById(Integer teacherid);
    List<Teacher> queryTeacher(String teachername);
    public List<StuAnswer> getQuestion(@Param("stuId")int stuId, @Param("paperId") int paperId, @Param("releaseExamId")int releaseExamId);
    List<Question> getQuestion1(@Param("majorId")int majorId, @Param("teachername")String teachername);
    List<StuAnswer> showanalyze(@Param("questionId") Integer questionId);
    List<QuestionA> getType(Integer questionId);



    //修改用户信息
    public boolean updateInformation(Teacher teacher);
    public LoginUser findByUsername(String username);
    public Teacher findTeaByUsername(String username);
    public Teacher getTeaByEmail(String teacheremail);
    //修改用户密码
    public boolean updatePassword(LoginUser user);
    //修改教师密码
    public boolean updateTeaPassword(Teacher teacher);
    public String login(@Param("username") String username, @Param("password") String password);
    public boolean findReisterUsername(String username);
    //注册用户账号
    public boolean register(LoginUser user);
    //注册教师用户账号信息存储
    public boolean addTeacherInfo(Teacher user);
}
