package com.onlineExam.service;


import com.onlineExam.domain.ReleaseExam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentFunctionService {
    Integer getStuMajor(@Param("username") String username);
    List<ReleaseExam> getReleaseByMajor(@Param("majorId") Integer majorId);
    int updateState(@Param("releaseExamId") Integer releaseExamId, @Param("examStateId") Integer examStateId);
    List<ReleaseExam> selectReleaseTime();
}
