package com.onlineExam.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinExamInfo {
    ReleaseExam releaseExam;
    String examInfo;
    int checkNumber;

    public JoinExamInfo(ReleaseExam releaseExam, int checkNumber) {
        this.releaseExam = releaseExam;
        this.checkNumber = checkNumber;
    }

    public JoinExamInfo(ReleaseExam releaseExam, String examInfo) {

        this.releaseExam = releaseExam;
        this.examInfo = examInfo;
    }
}
