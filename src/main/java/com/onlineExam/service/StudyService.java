package com.onlineExam.service;

import com.onlineExam.domain.StudyLocation;

import java.util.List;

public interface StudyService {
    int insertShare(int stuId,int questionId,String time);//分享论坛
    List<StudyLocation> getallList();//获取所有论坛内容
}
