package com.onlineExam.mapper;

import com.onlineExam.domain.StudyLocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudyMapper {
    int insertShare(@Param("stuId") int stuId,@Param("questionId")int questionId,@Param("time")String time);//分享论坛
    List<StudyLocation> getallList();//获取所有论坛内容

}
