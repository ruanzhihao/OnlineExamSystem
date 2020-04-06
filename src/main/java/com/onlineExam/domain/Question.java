package com.onlineExam.domain;

import lombok.Data;

@Data
/**
 * 题目
 */
public class Question {
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
    private Integer questionType;
    private Integer questionClass;

}
