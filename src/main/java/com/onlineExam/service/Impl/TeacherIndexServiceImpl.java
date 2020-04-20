package com.onlineExam.service.Impl;

import com.onlineExam.domain.Clazz;
import com.onlineExam.domain.Depart;
import com.onlineExam.domain.Question;
import com.onlineExam.domain.Student;
import com.onlineExam.mapper.TeacherIndexMapper;
import com.onlineExam.service.TeacherIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherIndexServiceImpl implements TeacherIndexService {

    @Autowired
    private TeacherIndexMapper teacherIndexMapper;
    @Override
    public List<Student> getAllStudent() {
        return teacherIndexMapper.getAllStudent();
    }

    @Override
    public List<Clazz> getAllClazz() {
        return teacherIndexMapper.getAllClazz();
    }

    @Override
    public List<Depart> getAllDepart() {
        return teacherIndexMapper.getAllDepart();
    }

    @Override
    public List<Student> queryStuInfo(int stuId) {
        return teacherIndexMapper.queryStuInfo(stuId);
    }

    @Override
    public List<Question> getAllQues() {
        return teacherIndexMapper.getAllQues();
    }

    @Override
    public List<Question> getTgQues(int courseId) {
        return teacherIndexMapper.getTgQues(courseId);
    }
}
