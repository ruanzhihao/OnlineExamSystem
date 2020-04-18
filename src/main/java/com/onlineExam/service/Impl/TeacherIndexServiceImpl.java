package com.onlineExam.service.Impl;

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
}
