package com.onlineExam.service.Impl;

import com.onlineExam.domain.*;
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
    @Override
    public List<Teacher> queryTeacher(String teachername) {
        return teacherMapper.queryTeacher(teachername);
    }

    @Override
    public List<StuAnswer> getQuestion(int stuId, int paperId, int releaseExamId) {
        return teacherMapper.getQuestion(stuId,paperId,releaseExamId);
    }

    @Override
    public List<Question> getQuestion1(int majorId, String teachername) {
        return teacherMapper.getQuestion1(majorId, teachername);
    }

    @Override
    public List<StuAnswer> showanalyze(Integer questionId) {
        return teacherMapper.showanalyze(questionId);
    }

    @Override
    public List<QuestionA> getType(Integer questionId) {
        return teacherMapper.getType(questionId);
    }


    //修改用户信息
    @Override
    public boolean updateInformation(Teacher teacher) {
        if (teacherMapper.updateInformation(teacher) == 0) {
            return false;
        } else
            return true;
    }
    @Override
    public LoginUser findByUsername(String username) {
        return teacherMapper.findByUsername(username);
    }
    @Override
    public Teacher findTeaByUsername(String username) {
        return teacherMapper.findTeaByUsername(username);
    }

    @Override
    public Teacher getTeaByEmail(String teacheremail) {
        return teacherMapper.getTeaByEmail(teacheremail);
    }

    @Override
    public boolean updatePassword(LoginUser user) {
        if (teacherMapper.updatePassword(user)==0) {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean updateTeaPassword(Teacher teacher) {
        if (teacherMapper.updateTeaPassword(teacher)==0) {
            return false;
        }else {
            return true;
        }
    }
    @Override
    public String login(String username, String password) {
        return teacherMapper.login(username,password);
    }
    //注册判断用户名是否存在
    @Override
    public boolean findReisterUsername(String username) {
        if (teacherMapper.findRegisterUsername(username) == null) {
            return false;
        } else
            return true;
    }
    //判断注册是否增加成功
    @Override
    public boolean register(LoginUser user) {
        int appregister = 0;
        appregister = teacherMapper.register(user);
        // System.out.println("appregister:"+appregister);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }
    //增加用户信息
    @Override
    public boolean addTeacherInfo(Teacher user) {
        int appregister = 0;
        appregister = teacherMapper.addTeacher(user);
        if (appregister == 0) {
            return false;
        } else {
            return true;
        }
    }
}
