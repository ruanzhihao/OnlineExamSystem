package com.onlineExam.service;

import com.onlineExam.domain.Teacher;

import java.util.List;


public interface TeacherService {
    public List<Teacher> getAllTeacher();//获取所有的老师信息
    public int addTeacher(Teacher teacher);//添加教师信息
    public int deleteTeacher(Integer teacherid);
    public int updateTeacher(Teacher teacher);
    public Teacher findTeacherById(Integer teacherid);
    List<Teacher> queryTeacher(String teachername);
}
