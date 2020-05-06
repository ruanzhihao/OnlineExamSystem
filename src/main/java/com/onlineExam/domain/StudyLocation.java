package com.onlineExam.domain;

import lombok.Data;

/*
* 分享到学习论坛
* */
@Data
public class StudyLocation {
    int id;
    int questionId;
    String talking;//提问的叙述
    int stuId;
    String sharetime;
    int examId;
    String examName;

    private String questionName;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
    private int questionScore;
    private Integer courseId;
    private String courseName;
    private Integer majorId;
    private String majorName;
    private String questionType;
    private String questionClass;
    private String paperName;

    private String username;
    private Integer stuid;
    private String stuname;
    private String stupassword;
    private String stuphonenumber;
    private String stuemail;
    private int departId;
    private String departName;
    private int stateId;
    private String state;
    private int clazzId;
    private String clazzName;


    //评论
    int StudyLocationId;//社区tgId
    /*    int questionId;//对应question的Id
        int stuId;//回复人的学号*/
    String stuName;
    String tgStuName;
    int tgStuId;//提问被回复人的stuId
    String comments;//评论内容
    String publicTime;//评论回复的时间
}
