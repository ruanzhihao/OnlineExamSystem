package com.onlineExam.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinExamInfo {
    ReleaseExam releaseExam;
    String examInfo;
}
