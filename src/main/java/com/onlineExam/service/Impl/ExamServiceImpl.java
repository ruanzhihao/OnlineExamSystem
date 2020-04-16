package com.onlineExam.service.Impl;

import com.onlineExam.domain.Exam;
import com.onlineExam.domain.Paper;
import com.onlineExam.domain.Student;
import com.onlineExam.mapper.ExamMapper;
import com.onlineExam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamMapper examMapper;


    @Override
    public int StuInsertExam(String examName, String stuId, String stuName, String stuDept, String stuClazz, String examTime, int examScore) {
        return examMapper.StuInsertExam(examName,stuId,stuName,stuDept,stuClazz,examTime,examScore);
    }

    @Override
    public Student fingStuById(String stuId) {
        return examMapper.fingStuById(stuId);
    }

    @Override
    public Paper findPaperById(int paperId) {
        return examMapper.findPaperById(paperId);
    }

    @Override
    public List<Exam> findExamScoreById(String stuId) {
        return examMapper.findExamScoreById(stuId);
    }
}
