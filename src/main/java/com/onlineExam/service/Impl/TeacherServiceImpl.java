package com.onlineExam.service.Impl;

import com.onlineExam.domain.Teacher;
import com.onlineExam.mapper.TeacherMapper;
import com.onlineExam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;
    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAllTeacher();
    }
    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }
    @Override
    public int deleteTeacher(Integer teacherid) {
        return teacherMapper.deleteTeacher(teacherid);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Teacher findTeacherById(Integer teacherid) {
        Teacher t=teacherMapper.findTeacherById(teacherid);
        return t;
    }
}
