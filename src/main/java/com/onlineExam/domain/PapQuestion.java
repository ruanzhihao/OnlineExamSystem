package com.onlineExam.domain;

import lombok.Data;

@Data
public class PapQuestion {
    private Integer realPaperId;
    private Integer questionId;
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
}
