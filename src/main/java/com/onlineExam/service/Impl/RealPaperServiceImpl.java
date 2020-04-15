package com.onlineExam.service.Impl;

import com.onlineExam.domain.RealPaper;
import com.onlineExam.mapper.RealPaperMapper;
import com.onlineExam.service.RealPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RealPaperServiceImpl implements RealPaperService {
    @Autowired
    private RealPaperMapper realPaperMapper;

    @Override
    public RealPaper selectOneUnique(Integer paperId, Integer questionId) {
        return realPaperMapper.selectOneUnique(paperId,questionId);
    }

    @Override
    public int countQuestionNumber(Integer paperId) {
        return realPaperMapper.countQuestionNumber(paperId);
    }

    @Override
    public int addOnePap(Integer paperId, Integer questionId) {
        return realPaperMapper.addOnePap(paperId,questionId);
    }

    @Override
    public int deletePapQues(Integer realPaperId) {
        return realPaperMapper.deletePapQues(realPaperId);
    }

    @Override
    public int addRealPaper(Map<String, Object> map) {
        return realPaperMapper.addRealPaper(map);
    }
}
