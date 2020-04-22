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
}
