package com.onlineExam.mapper;

import com.onlineExam.domain.Clazz;
import com.onlineExam.domain.Depart;
import com.onlineExam.domain.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherIndexMapper {
   List<Student> getAllStudent();//学生信息 获取学生
   List<Clazz>  getAllClazz();//获取全部班级生成动态框
   List<Depart> getAllDepart();//获取全部部门信息
   List<Student> queryStuInfo(@Param("stuId") int stuId);//查看学生信息
}
