package com.onlineExam.service;

import com.onlineExam.domain.StudyLocation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyService {
    int insertShare(int stuId,int questionId,String time);//分享论坛
    List<StudyLocation> getallList();//获取所有论坛内容
    //我的话题
    List<StudyLocation> getAllByOwn(@Param("stuId") int stuId);
    //分类显示
    List<StudyLocation> getAllFenlei(@Param("courseId") int courseId);
}
