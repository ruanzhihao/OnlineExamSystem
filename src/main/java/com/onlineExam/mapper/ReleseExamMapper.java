package com.onlineExam.mapper;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ReleseExamMapper {
    List<Depart> getAllDepart();
    List<Paper>  getPaperName();
    //查询考试状态
    List<ExamState> getAllState();
    //查询所有考务
    List<ReleaseExam> getAllReleaseInfo();
    //根据Id查询
    ReleaseExam getReleaseExamById(@Param("releaseExamId") Integer releaseExamId);
    //删除考务
    int deleteReleaseInfoById(@Param("releaseExamId") Integer releaseExamId);
    int releaseExam(@Param("departId")Integer departId , @Param("majorId") Integer majorId, @Param("courseId")Integer courseId, @Param("beginTime") String beginTime, @Param("authorId") Integer authorId, @Param("paperId") Integer paperId);
   //更新
    int updateReleaseExam(@Param("releaseExamId") Integer releaseExamId,@Param("departId")Integer departId , @Param("majorId") Integer majorId, @Param("courseId")Integer courseId, @Param("beginTime") String beginTime, @Param("authorId") Integer authorId, @Param("paperId") Integer paperId);
    List<ReleaseExam> getReleaseInfoByStateId(@Param("examStateId") Integer examStateId);
    int getJoinReleseClassCount(@Param("stuClazz") String stuClazz,@Param("releaseExamId") Integer releaseExamId);
    List<String> getJoinReleseClass(@Param("releaseExamId") Integer releaseExamId);
    int  getAvg(@Param("stuClazz") String stuClazz,@Param("releaseExamId") Integer releaseExamId);
    List<ReleaseExam> getAllReleaseInfoByTeacherId(@Param("authorId") Integer authorId);
    List<Teacher> getAllTeacher();

}
