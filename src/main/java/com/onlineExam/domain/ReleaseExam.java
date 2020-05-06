package com.onlineExam.domain;

import lombok.Data;

@Data
public class ReleaseExam {
    Integer releaseExamId;
    Integer departId;
    String departName;
    Integer majorId;
    String majorName;
    Integer courseId;
    String courseName;
    int authorId;
    String beginTime;
    int stuId;
    String authorName;
    String stuname;
    Integer questionNumber;
    Integer sumQuestionScore;
    Integer paperId;
    String paperName;
    Integer answerTime;
    Integer examStateId;
    String examStateName;
    int JoinNumber;
}