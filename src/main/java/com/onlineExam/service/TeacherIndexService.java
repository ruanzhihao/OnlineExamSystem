package com.onlineExam.service;

import com.onlineExam.domain.Clazz;
import com.onlineExam.domain.Depart;
import com.onlineExam.domain.Question;
import com.onlineExam.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherIndexService {
    List<Student> getAllStudent();//学生信息 获取学生
    List<Clazz>  getAllClazz();//获取全部班级生成动态框
    List<Depart> getAllDepart();//获取全部部门信息
    List<Student> queryStuInfo(int stuId);//查看学生信息
    List<Question> getAllQues();//获取全部试题
    List<Question> getTgQues(int courseId);//根据course搜索试题
}
