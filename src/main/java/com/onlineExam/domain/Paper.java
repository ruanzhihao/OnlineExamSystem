package com.onlineExam.domain;

import lombok.Data;

@Data
public class Paper {
    Integer paperId;
    String paperName;
    int majorId;
    String paperClass;
    String majorName;
    int questionNumber;
    int sumQuestionScore;
    int answerTime;
}
