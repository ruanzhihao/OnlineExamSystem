package com.onlineExam.service.Impl;

import com.onlineExam.domain.Exam;
import com.onlineExam.domain.Paper;
import com.onlineExam.domain.Student;
import com.onlineExam.mapper.ExamMapper;
import com.onlineExam.service.ExamService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;


    @Override
    public int StuInsertExam(String examName, int stuId, String stuName, String stuDept, String stuClazz, String examTime, int examScore, int paperid, Integer releaseExamId) {
        return examMapper.StuInsertExam(examName,stuId,stuName,stuDept,stuClazz,examTime,examScore,paperid,releaseExamId);
    }

    @Override
    public Student fingStuById(int stuId) {
        return examMapper.fingStuById(stuId);
    }

    @Override
    public Paper findPaperById(int paperId) {
        return examMapper.findPaperById(paperId);
    }

    @Override
    public List<Exam> findExamScoreById(int stuId) {
        return examMapper.findExamScoreById(stuId);
    }
}
