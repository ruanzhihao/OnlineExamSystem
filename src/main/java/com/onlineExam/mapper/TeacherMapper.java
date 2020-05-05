package com.onlineExam.mapper;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TeacherMapper {

    List<Teacher> getAllTeacher(); //获取所有的老师信息
    int addTeacher(Teacher teacher);//添加教师
    int deleteTeacher(Integer teacherid);//删除教师信息
    int updateTeacher(Teacher teacher);//修改信息
    Teacher findTeacherById(Integer teacherid);//根据id查找
    List<Teacher> queryTeacher(@Param("teachername") String teachername);//根据教师姓名查询
    //查看学生答题情况
    List<StuAnswer> getQuestion(@Param("stuId")int stuId, @Param("paperId") int paperId, @Param("releaseExamId")int releaseExamId);
    //显示试题
    List<Question> getQuestion1(@Param("majorId") int majorId, @Param("teachername") String teachername);
    List<StuAnswer> showanalyze(@Param("questionId") Integer questionId);
    List<QuestionA> getType(Integer questionId);


    //修改用户信息
    public int updateInformation(Teacher teacher);
    //登录验证
    public LoginUser findByUsername(String username);
    public Teacher findTeaByUsername(String username);
    public Teacher getTeaByEmail(String teacheremail);
    //修改用户密码
    public int updatePassword(LoginUser user);
    //修改教师密码
    public int updateTeaPassword(Teacher teacher);
    //登录
    public String login(@Param("username") String username, @Param("password") String password);
    //判断用户名是否注册过
    public LoginUser findRegisterUsername(String username);
    //注册用户账号
    public int register(LoginUser user);
    //注册教师用户账号信息存储
    public int addTeacherInfo(Teacher user);

}
