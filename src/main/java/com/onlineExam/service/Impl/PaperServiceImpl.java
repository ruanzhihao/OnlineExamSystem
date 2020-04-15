package com.onlineExam.service.Impl;

import com.onlineExam.domain.*;
import com.onlineExam.mapper.PaperMapper;
import com.onlineExam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {

@Autowired
     private PaperMapper paperMapper;
    @Override
    public int addPaperService(String paperName,String paperClass,int questionNumber,int answerTime,int sumQuestionScore,int majorId) {
        return paperMapper.addPaper(paperName,paperClass,questionNumber,answerTime,sumQuestionScore,majorId);
    }

    @Override
    public List<Paper> getAllPaper() {
        return paperMapper.getAllPaper();
    }

    @Override
    public Paper findPaperById(Integer paperId) {
        return paperMapper.findPaperById(paperId);
    }

    @Override
    public int updatePaper(Paper paper) {
        return paperMapper.updatePaper(paper);
    }

    @Override
    public List<Paper> findPaperByMajorId(Integer majorId) {
        return paperMapper.findPaperByMajorId(majorId);
    }

    @Override
    public int updatePaperScore(Map<String, Object> map) {
        return paperMapper.updatePaperScore(map);
    }

    @Override
    public List<Course> getAllCourse() {
        return paperMapper.getAllCourse();
    }

    @Override
    public int updatePaperQuestionNumber(Map<String, Object> map) {
        return paperMapper.updatePaperQuestionNumber(map);
    }

    @Override
    public  List<Major>  getAllMajor() {
        return paperMapper.getAllMajor();
    }

    @Override
    public  List<EasyClass> getAllEasyClass() {
        return paperMapper.getAllEasyClass();
    }

    @Override
    public int updatePaperScoreSimpl(Map<String, Object> map) {
        return paperMapper.updatePaperScoreSimpl(map);
    }

    @Override
    public int updatePaperQuestionNumber1Simpl(Map<String, Object> map) {
        return paperMapper.updatePaperQuestionNumber1Simpl(map);
    }

    @Override
    public int findSumquestionScore(Integer paperId) {
        return paperMapper.findSumquestionScore(paperId);
    }

    @Override
    public List<PapQuestion> getPapQues(Integer paperId) {
        return paperMapper.getPapQues(paperId);
    }

    @Override
    public int deletePaperById(Integer paperId) {
        return paperMapper.deletePaperById(paperId);
    }
}
