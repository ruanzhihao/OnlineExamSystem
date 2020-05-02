package com.onlineExam.service.Impl;

import com.onlineExam.domain.StudyLocation;
import com.onlineExam.mapper.StudyMapper;
import com.onlineExam.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyMapper studyMapper;
    @Override
    public int insertShare(int stuId, int questionId,String time) {
        return studyMapper.insertShare(stuId, questionId,time);
    }

    @Override
    public List<StudyLocation> getallList() {
        return studyMapper.getallList();
    }
}
