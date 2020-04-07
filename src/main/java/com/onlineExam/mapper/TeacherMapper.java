package com.onlineExam.mapper;

import com.onlineExam.domain.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface TeacherMapper {

    List<Teacher> getAllTeacher(); //获取所有的老师信息

}
