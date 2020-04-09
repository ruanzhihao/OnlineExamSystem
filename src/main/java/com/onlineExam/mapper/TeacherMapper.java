package com.onlineExam.mapper;

import com.onlineExam.domain.Teacher;
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
}
