package com.onlineExam.service.Impl;

import com.onlineExam.domain.StudyLocation;
import com.onlineExam.domain.StudyLocationA;
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

    @Override
    public List<StudyLocation> getAllByOwn(int stuId) {
        return studyMapper.getAllByOwn(stuId);
    }

    @Override
    public List<StudyLocation> getAllFenlei(int courseId) {
        return studyMapper.getAllFenlei(courseId);
    }

    @Override
    public List<StudyLocationA> getTop() {
        return studyMapper.getTop();
    }
}
