package com.onlineExam.domain;

import lombok.Data;

/*
* 考试成绩记录
* */
@Data
public class Exam {
    int examid;
    String examName;
    int stuId;//学生号
    String stuName;//学生名
    int departId;
    String stuDept;
    int courseId;
    String stuCourse;
    int majorId;
    String stuClazz;
    String stuMajor;
    String examTime;
    int examScore;
    int paperId;
}
