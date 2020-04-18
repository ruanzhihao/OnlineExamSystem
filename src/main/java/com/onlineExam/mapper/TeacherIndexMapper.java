package com.onlineExam.mapper;

import com.onlineExam.domain.Student;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherIndexMapper {
   List<Student> getAllStudent();//学生信息 获取学生


}
