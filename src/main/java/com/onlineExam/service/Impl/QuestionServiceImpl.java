package com.onlineExam.service.Impl;

import com.onlineExam.domain.Course;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Question;
import com.onlineExam.domain.QuestionVo;
import com.onlineExam.mapper.QuestionMapper;
import com.onlineExam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestion() {
        return questionMapper.getAllQuestion();
    }

    @Override
    public List<Course> getCourse() {
        return questionMapper.getCourse();
    }

    @Override
    public List<Major> getMajor() {
        return questionMapper.getMajor();
    }

    @Override
    public int addQuestion(String questionName, String optionA, String optionB, String optionC, String optionD, String answer, int questionScore, int courseId, int majorId, String questionType, String questionClass) {
        return questionMapper.addQuestion(questionName,optionA,optionB,optionC,optionD,answer,questionScore,courseId,majorId,questionType,questionClass);
    }
    @Override
    public int addImportQuestion(Integer questionId, String questionName, String optionA, String optionB,
                                 String optionC, String optionD, String answer, Integer questionScore, Integer
                                         courseId, Integer majorId, String questionType, String questionClass) {
        return questionMapper.addImportQuestion(questionId, questionName, optionA, optionB,
                optionC,  optionD,  answer, questionScore,
                courseId,  majorId,  questionType, questionClass);
    }

    @Override
    public int delQuestionById(int questionId) {
        return questionMapper.delQuestionById(questionId);
    }

    @Override
    public List<Question> queryQuestion(String questionName) {
        return questionMapper.queryQuestion(questionName);
    }

    @Override
    public Question showQuestion(Integer questionId) {
        return questionMapper.showQuestion(questionId);
    }

    @Override
    public int updateQuestion(Integer questionId, String questionName, String optionA, String optionB, String optionC, String optionD, String answer, Integer questionScore, Integer courseId, Integer majorId, String questionType, String questionClass) {
        return questionMapper.updateQuestion(questionId, questionName, optionA, optionB,
                optionC,  optionD,  answer, questionScore,
                courseId,  majorId,  questionType, questionClass);
    }

    @Override
    public List<Integer> getQuestionId(int count) {
        return questionMapper.getQuestionId(count);
    }
    @Override
    public List<Major> getAllMajor() {
        return questionMapper.getAllMajor();
    }

    @Override
    public List<Course> getAllCourse() {
        return questionMapper.getAllCourse();
    }

    @Override
    public List<Question> getAllQuestionByMajorId(Integer majorId) {
        return questionMapper.getAllQuestionByMajorId(majorId);
    }

    @Override
    public List<QuestionVo> getAllQuestionVo() {
        return questionMapper.getAllQuestionVo();
    }

    @Override
    public List<QuestionVo> getQuestionType() {
        return questionMapper.getQuestionType();
    }
}
