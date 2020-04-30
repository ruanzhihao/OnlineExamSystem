package com.onlineExam.service;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReleaseExamService {
    List<Depart> getAllDepart();
    List<Paper>  getPaperName();
    int releaseExam(@Param("departId")Integer departId , @Param("majorId") Integer majorId, @RequestParam("courseId")Integer courseId, @Param("beginTime") String beginTime, @Param("authorId") Integer authorId, @Param("paperId") Integer paperId);
    List<ReleaseExam> getAllReleaseInfo();
    List<ExamState> getAllState();
    int deleteReleaseInfoById(@Param("releaseExamId") Integer releaseExamId);
    ReleaseExam getReleaseExamById(Integer releaseExamId);
    int updateReleaseExam(@Param("releaseExamId") Integer releaseExamId,@Param("departId")Integer departId , @Param("majorId") Integer majorId, @Param("courseId")Integer courseId, @Param("beginTime") String beginTime, @Param("authorId") Integer authorId, @Param("paperId") Integer paperId);
    List<ReleaseExam> getReleaseInfoByStateId(@Param("examStateId") Integer examStateId);
    int getJoinReleseClassCount(String stuClazz,Integer releaseExamId);
    List<String> getJoinReleseClass(Integer releaseExamId);
    int getAvg(String stuClazz,Integer releaseExamId);
    List<ReleaseExam> getAllReleaseInfoByTeacherId(@Param("authorId") Integer authorId);
    List<Teacher> getAllTeacher();
}
