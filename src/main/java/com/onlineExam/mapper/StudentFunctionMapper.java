package com.onlineExam.mapper;

import com.onlineExam.domain.Paper;

import com.onlineExam.domain.ReleaseExam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentFunctionMapper {
    //查询对应学生major
    Integer getStuMajor(@Param("username") String username);
    //根据major查询对应专业的考务信息
    List<ReleaseExam> getReleaseByMajor(@Param("majorId") Integer majorId);
    //查询对应的answerTime
    Paper getAnswerTime(@Param("paperId") Integer paperId);
    //更改状态
    int updateState(@Param("releaseExamId") Integer releaseExamId, @Param("examStateId") Integer examStateId);
    //学生端考务展示
    List<ReleaseExam> selectReleaseTime();
}
