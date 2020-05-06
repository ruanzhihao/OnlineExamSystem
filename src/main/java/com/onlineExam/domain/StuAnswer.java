package com.onlineExam.domain;

import lombok.Data;
@Data
public class StuAnswer {
    Integer stuAnswerId;
    Integer stuId;
    Integer paperId;
    Integer questionId;
    String stuAnswer;
    Integer releaseExamId;
    Integer isWrong;
    String isCollect;
    String isCheck;
    int teaCheckScore;
    String teaSuggest;
    String questionName;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;
    int questionScore;
    String questionType;
    String questionClass;
    String stuname;
    String type;
}
