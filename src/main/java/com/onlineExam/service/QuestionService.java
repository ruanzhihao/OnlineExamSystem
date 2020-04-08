package com.onlineExam.service;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionService {
    public List<Question> getAllQuestion();//获取所有题目信息
    //生成动态学科下拉框
    List<Course> getCourse();

    //生成动态专业下拉框
    List<Major> getMajor();

    //增加题目的实现
    public int addQuestion(String questionName,String optionA,String optionB,String optionC,String optionD,String answer,int questionScore,int courseId,int majorId,String questionType,String questionClass);

    List<Course> getAllCourse();
    List<Major> getAllMajor();
    /*int addQuestions(Map<String,Object> questions);*/
    int addImportQuestion(@Param("questionId") Integer questionId, @Param("questionName") String questionName, @Param("optionA") String optionA, @Param("optionB") String optionB, @Param("optionC") String optionC, @Param("optionD") String optionD, @Param("answer") String answer, @Param("questionScore") Integer questionScore, @Param("courseId") Integer courseId, @Param("majorId") Integer majorId, @Param("questionType") String questionType, @Param("questionClass") String questionClass);
    //根据试题Id  删除
    int delQuestionById(@Param("questionId") int questionId);

    //根据试题内容模糊查询
    List<Question> queryQuestion(String questionName);
}
