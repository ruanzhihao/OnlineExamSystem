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
String authorName;
Integer questionNumber;
Integer sumQuestionScore;
Integer paperId;
String paperName;
Integer answerTime;
Integer examStateId;
String examStateName;
}
