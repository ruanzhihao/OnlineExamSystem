package com.onlineExam.service.Impl;


import com.onlineExam.domain.*;
import com.onlineExam.mapper.StuUserMapper;
import com.onlineExam.service.StuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StuUserServiceImpl implements StuUserService {
    //属性注入
    @Autowired
    private StuUserMapper usermapper;

    @Override
    public List<StuAnswer> getErrorQuestion(Integer stuId) {
        return usermapper.getErrorQuestion(stuId);
    }
    @Override
    public Student getStuByEmail(String stuemail) {
        return usermapper.getStuByEmail(stuemail);
    }

    @Override
    public String login(String username, String password) {
        return usermapper.login(username,password);
    }

    //登录验证
    @Override
    public LoginUser findByUsername(String username) {
        return usermapper.findByUsername(username);
    }

    //注册判断用户名是否存在
    @Override
    public boolean findReisterUsername(String username) {
        if (usermapper.findRegisterUsername(username) == null) {
            return false;
        } else
            return true;
    }

    //判断注册是否增加成功
    @Override
    public boolean register(LoginUser user) {
        int appregister = 0;
        appregister = usermapper.register(user);
        // System.out.println("appregister:"+appregister);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }


    //查询用户信息
    @Override
    public Student findInformationByUsername(String username) {
        return usermapper.findInformationByUsername(username);
    }

    //增加用户信息
    @Override
    public boolean addStuInformation(Student user) {
        int appregister = 0;
        appregister = usermapper.addStuInformation(user);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }

    //修改用户密码
    @Override
    public boolean updatePassword(LoginUser user) {
        if (usermapper.updatePassword(user) == 0) {
            return false;
        } else
            return true;
    }
    //修改学生密码
    @Override
    public boolean updateStuPassword(Student  student) {
        if (usermapper.updateStuPassword(student)== 0) {
            return false;
        } else
            return true;
    }

    //修改用户信息
    @Override
    public boolean updateInformation(Student user) {
        if (usermapper.updateInformation(user) == 0) {
            return false;
        } else
            return true;
    }
    @Override
    public List<Student1> getStudentList() {
        return usermapper.getStudentList();
    }

    @Override
    public int addStudent(Student1 student1) {
        return usermapper.addStudent(student1);
    }

    @Override
    public int deleteStudent(Integer id) {
        return usermapper.deleteStudent(id);
    }

    @Override
    public int updateStudent(Student1 student1) {
        return usermapper.updateStudent(student1);
    }

    @Override
    public Student1 findStudentById(Integer id) {
        Student1 s=usermapper.findStudentById(id);
        return s;
    }
    @Override
    public List<Student1> findStudentByclazzId(Integer clazzId) {
        return usermapper.findStudentByclazzId(clazzId);
    }

    @Override
    public List<Student1> queryStudent(String stuname) {
        return usermapper.queryStudent(stuname);
    }

    @Override
    public List<Student1> queryStudent1(String stuname) {
        return usermapper.queryStudent1(stuname);
    }

    @Override
    public List<Clazz> getClazzList() {
        return usermapper.getClazzList();
    }

    @Override
    public List<Major> getMajorList() {
        return usermapper.getMajorList();
    }

    @Override
    public List<Depart> getDepartList() {
        return usermapper.getDepartList();
    }

    @Override
    public List<State> getStateList() {
        return usermapper.getStateList();
    }

    @Override
    public List<Exam> getScoreBystuname(String stuname) {
        return usermapper.getScoreBystuname(stuname);
    }

}