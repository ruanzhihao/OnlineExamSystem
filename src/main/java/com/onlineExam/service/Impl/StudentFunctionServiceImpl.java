package com.onlineExam.service.Impl;


import com.onlineExam.domain.Question;
import com.onlineExam.domain.ReleaseExam;
import com.onlineExam.domain.StuAnswer;
import com.onlineExam.mapper.StudentFunctionMapper;
import com.onlineExam.service.StudentFunctionService;
import org.apache.ibatis.annotations.Param;
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
    public int getCheckBoxCount(Integer paperId) {
        return studentFunctionMapper.getCheckBoxCount(paperId);
    }

    @Override
    public int insertExamRecord(Map<Object, Object> recordMap) {
        return studentFunctionMapper.insertExamRecord(recordMap);
    }

    @Override
    public List<Question> getCheckQuestion(Integer paperId) {
        return studentFunctionMapper.getCheckQuestion(paperId);
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
    public List<StuAnswer> getHistory(int paperId, int stuId) {
        return studentFunctionMapper.getHistory(paperId, stuId);
    }

    @Override
    public List<StuAnswer> getCheckHistory(int paperId, int stuId) {
        return studentFunctionMapper.getCheckHistory(paperId, stuId);
    }
}
