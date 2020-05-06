package com.onlineExam.domain;

import lombok.Data;

/*
* 学习社区评论回复表
* */
@Data
public class Comment {
    int id;
    int studyId;//社区tgId
    int questionId;//对应question的Id
    int stuId;//回复人的学号
    String stuName;
    int tgStuId;//提问被回复人的stuId
    String tgStuName;
    String comments;//评论内容
    String publicTime;//评论回复的时间
    //学生类
    private String username;
    private Integer stuid;
    private String stuname;
    private String stupassword;
    private String stuphonenumber;
    private String stuemail;
    private int majorId;
    private String majorName;
    private int departId;
    private String departName;
    private int stateId;
    private String state;
    private int clazzId;
    private String clazzName;

}
