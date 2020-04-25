package com.onlineExam.mapper;

import com.onlineExam.domain.Exam;
import com.onlineExam.domain.Paper;
import com.onlineExam.domain.Student;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamMapper {
    //examName,stuId,stuName,stuDept,stuClazz,examTime,examScore
    int StuInsertExam(@Param("examName") String examName, @Param("stuId") int stuId, @Param("stuName") String stuName, @Param("stuDept") String stuDept, @Param("stuClazz") String stuClazz, @Param("examTime") String examTime, @Param("examScore") int examScore,@Param("paperid") int paperid,@Param("releaseExamId") Integer releaseExamId);//考试结束 添加考试记录
    Student fingStuById(@Param("stuId") int stuId);//根据学生Id查询学生信息
    Paper findPaperById(@Param("paperId") int paperId);//根据试卷Id查询试卷信息
    List<Exam> findExamScoreById(int stuId);//学生查看自己的考试记录
}
