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
    Integer releaseExamId;
    int countClass;
    int avgScore;

    public Exam(int examid, String examName, int stuId, String stuName, int departId, String stuDept, int courseId, String stuCourse, int majorId, String stuClazz, String stuMajor, String examTime, int examScore, int paperId) {
        this.examid = examid;
        this.examName = examName;
        this.stuId = stuId;
        this.stuName = stuName;
        this.departId = departId;
        this.stuDept = stuDept;
        this.courseId = courseId;
        this.stuCourse = stuCourse;
        this.majorId = majorId;
        this.stuClazz = stuClazz;
        this.stuMajor = stuMajor;
        this.examTime = examTime;
        this.examScore = examScore;
        this.paperId = paperId;
    }

    public Exam() {

    }

    public Exam(String stuClazz, int countClass) {
        this.stuClazz = stuClazz;
        this.countClass = countClass;
    }

    public Exam(String stuClazz, int countClass, int avgScore) {
        this.stuClazz = stuClazz;
        this.countClass = countClass;
        this.avgScore = avgScore;
    }
}
