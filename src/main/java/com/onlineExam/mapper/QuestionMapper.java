package com.onlineExam.mapper;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper {

    List<Question> getAllQuestion();//获取所有题目信息

    //生成动态学科下拉框
    List<Course> getCourse();

    //生成动态专业下拉框
    List<Major> getMajor();

    //增加题目的实现
    int addQuestion(@Param("questionName") String questionName, @Param("optionA") String optionA, @Param("optionB") String optionB, @Param("optionC") String optionC,
                    @Param("optionD") String optionD, @Param("answer") String answer, @Param("questionScore") int questionScore, @Param("courseId") int courseId, @Param("majorId") int majorId,
                    @Param("questionType") String questionType, @Param("questionClass") String questionClass);
    List<Course> getAllCourse();
    List<Major> getAllMajor();
    int addImportQuestion(@Param("questionId") Integer questionId, @Param("questionName") String questionName, @Param("optionA") String optionA, @Param("optionB") String optionB, @Param("optionC") String optionC, @Param("optionD") String optionD, @Param("answer") String answer, @Param("questionScore") Integer questionScore, @Param("courseId") Integer courseId, @Param("majorId") Integer majorId, @Param("questionType") String questionType, @Param("questionClass") String questionClass);

    //根据试题Id  删除
    int delQuestionById(@Param("questionId") int questionId);

    //根据试题内容模糊查询
    List<Question> queryQuestion(@Param("questionName") String questionName);

    //点击修改按钮 ==数据回显
    Question showQuestion(Integer questionId);

    //修改试题实现
    int updateQuestion(@Param("questionId") Integer questionId, @Param("questionName") String questionName, @Param("optionA") String optionA, @Param("optionB") String optionB, @Param("optionC") String optionC, @Param("optionD") String optionD, @Param("answer") String answer, @Param("questionScore") Integer questionScore, @Param("courseId") Integer courseId, @Param("majorId") Integer majorId, @Param("questionType") String questionType, @Param("questionClass") String questionClass);

    List<Integer> getQuestionId(int count);
    //根据MajorId查询Ques
    List<Question> getAllQuestionByMajorId(@Param("majorId") Integer majorId);
    //查询各个专业的题目数量
    List<QuestionVo> getAllQuestionVo();
    //查询题目类型de数量
    List<QuestionVo>  getQuestionType();
    List<CountVo> getResourceInfo();
    List<CountVo> getUserInfo();
}

