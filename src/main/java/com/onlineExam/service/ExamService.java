package com.onlineExam.service;

import com.onlineExam.domain.CountVo;
import com.onlineExam.domain.Exam;
import com.onlineExam.domain.Paper;
import com.onlineExam.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamService {
    int StuInsertExam(String examName, int stuId, String stuName, String stuDept, String stuClazz, String examTime, int examScore, int paperid, Integer releaseExamId);//考试结束 添加考试记录
    Student fingStuById(int stuId);//根据学生Id查询学生信息
    Paper findPaperById(int paperId);//根据试卷Id查询试卷信息
    List<Exam> findExamScoreById(int stuId);//学生查看自己的考试记录
    List<CountVo> getResource();
}
