package com.onlineExam.service;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;

import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestion();//获取所有题目信息
    //生成动态学科下拉框
    List<Course> getCourse();

    //生成动态专业下拉框
    List<Major> getMajor();

    //增加题目的实现
    public int addQuestion(String questionName,String optionA,String optionB,String optionC,String optionD,String answer,int questionScore,int courseId,int majorId,String questionType,String questionClass);

}
