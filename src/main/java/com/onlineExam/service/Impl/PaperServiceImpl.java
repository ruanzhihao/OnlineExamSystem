package com.onlineExam.service.Impl;

import com.onlineExam.domain.EasyClass;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Paper;
import com.onlineExam.mapper.PaperMapper;
import com.onlineExam.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public  List<Major>  getAllMajor() {
        return paperMapper.getAllMajor();
    }

    @Override
    public  List<EasyClass> getAllEasyClass() {
        return paperMapper.getAllEasyClass();
    }

    @Override
    public int deletePaperById(Integer paperId) {
        return paperMapper.deletePaperById(paperId);
    }
}
