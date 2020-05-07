package com.onlineExam.service.Impl;



import com.onlineExam.domain.*;
import com.onlineExam.mapper.StudentFunctionMapper;
import com.onlineExam.service.StudentFunctionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentFunctionServiceImpl implements StudentFunctionService {
    @Autowired
    private StudentFunctionMapper studentFunctionMapper;

    @Override
    public Integer getStuMajor(String username) {
        return studentFunctionMapper.getStuMajor(username);
    }

    @Override
    public List<ReleaseExam> getReleaseByMajor(Integer majorId) {
        return studentFunctionMapper.getReleaseByMajor(majorId);
    }


    @Override
    public List<ReleaseExam> getTeaReleaseExam(int authorId) {
        return studentFunctionMapper.getTeaReleaseExam(authorId);
    }

    @Override
    public int waitCheckNumber(Integer releaseExamId) {
        return studentFunctionMapper.waitCheckNumber(releaseExamId);
    }

    @Override
    public int getCheckBoxCount(Integer paperId) {
        return studentFunctionMapper.getCheckBoxCount(paperId);
    }

    @Override
    public int insertExamRecord(Map<Object, Object> recordMap) {
        return studentFunctionMapper.insertExamRecord(recordMap);
    }

    @Override
    public int updateShortAnswer(Map<String,Object> teaCheckScoreMap) {
        return studentFunctionMapper.updateShortAnswer(teaCheckScoreMap);
    }

    @Override
    public List<StuAnswer> stuShortAnswer(Integer releaseExamId, Integer stuId) {
        return studentFunctionMapper.stuShortAnswer(releaseExamId,stuId);
    }

    @Override
    public int updateIsCheck(Integer releaseExamId, Integer stuId) {
        return studentFunctionMapper.updateIsCheck(releaseExamId,stuId);
    }

    @Override
    public List<Question> getShortAnsScoreIntoPap(Integer releaseExamId) {
        return studentFunctionMapper.getShortAnsScoreIntoPap(releaseExamId);
    }

    @Override
    public int getSumScore(Map<String, Object> scoreMap) {
        return studentFunctionMapper.getSumScore(scoreMap);
    }

    @Override
    public List<Question> getCheckQuestion(Integer paperId) {
        return studentFunctionMapper.getCheckQuestion(paperId);
    }

    @Override
    public List<ReleaseExam> getUncheck(Integer releaseExamId) {
        return studentFunctionMapper.getUncheck(releaseExamId);
    }

    @Override
    public List<StuAnswer> useranswerRecord(Integer paperId, Integer releaseExamId) {
        return studentFunctionMapper.useranswerRecord(paperId,releaseExamId);
    }

    @Override
    public int getCheckBoxQuestionScore(Integer paperId) {
        return studentFunctionMapper.getCheckBoxQuestionScore(paperId);
    }

    @Override
    public List<ReleaseExam> selectReleaseTime() {
        return studentFunctionMapper.selectReleaseTime();
    }

    @Override
    public int setWrong(Integer stuAnswerId, Integer isWrong) {
        return studentFunctionMapper.setWrong(stuAnswerId,isWrong);
    }

    @Override
    public List<Question> questionAnswer(Integer paperId) {
        return studentFunctionMapper.questionAnswer(paperId);
    }

    @Override
    public int updateState(Integer releaseExamId, Integer examStateId) {
        return studentFunctionMapper.updateState(releaseExamId,examStateId);
    }



    @Override
    public List<Question> getRadioQuestion(Integer paperId) {
        return studentFunctionMapper.getRadioQuestion(paperId);
    }

    @Override
    public int getRadioCount(Integer paperId) {
        return studentFunctionMapper.getRadioCount(paperId);
    }

    @Override
    public int getRadioQuestionScore(Integer paperId) {
        return studentFunctionMapper.getRadioQuestionScore(paperId);
    }

    @Override
    public int getAnswerTime(Integer paperId) {
        return studentFunctionMapper.getAnswerTime(paperId);
    }

    @Override
    public String getExamInfoByStuId(Integer stuId, Integer releseExamId) {
        return studentFunctionMapper.getExamInfoByStuId(stuId,releseExamId);
    }

    @Override
    public List<StuAnswer> getHistory(int paperId, int stuId) {
        return studentFunctionMapper.getHistory(paperId, stuId);
    }

    @Override
    public List<StuAnswer> getCheckHistory(int paperId, int stuId) {
        return studentFunctionMapper.getCheckHistory(paperId, stuId);
    }
    @Override
    public List<Question> shortAnswerIdInPaper(Integer paperId) {
        return studentFunctionMapper.shortAnswerIdInPaper(paperId);
    }

    @Override
    public List<Question> getShortAnswer(Integer paperId) {
        return studentFunctionMapper.getShortAnswer(paperId);
    }

    @Override
    public int updateShortAnswerState(Map<Object, Object> shortAnswers) {
        return studentFunctionMapper.updateShortAnswerState(shortAnswers);
    }

    @Override
    public int getShortAnswerCount(Integer paperId) {
        return studentFunctionMapper.getShortAnswerCount(paperId);
    }

    @Override
    public int getShortAnswerScore(Integer paperId) {
        return studentFunctionMapper.getShortAnswerScore(paperId);
    }

    @Override
    public int insertComment(int studyId, int tgStuId, int stuId, String comments, String publicTime) {
        return studentFunctionMapper.insertComment(studyId, tgStuId, stuId, comments, publicTime);
    }

    @Override
    public Student studentById(int stuId) {
        return studentFunctionMapper.studentById(stuId);
    }

    @Override
    public List<Comment> showComment(int studyId, int tgStuId, int stuId) {
        return studentFunctionMapper.showComment(studyId, tgStuId, stuId);
    }

    @Override
    public List<StuAnswer> getShortAnswerAll(int paperId, int stuId) {
        return studentFunctionMapper.getShortAnswerAll(paperId,stuId);
    }
}
