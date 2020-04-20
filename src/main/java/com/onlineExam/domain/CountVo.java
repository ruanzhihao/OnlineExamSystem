package com.onlineExam.domain;

import lombok.Data;

@Data
public class CountVo {
    String countName;
    Integer questionNum;
    Integer  papNum;
    Integer releaseNum;
    Integer teacherNum;
    Integer stuNum;
}
