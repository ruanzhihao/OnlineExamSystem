package com.onlineExam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealPaper {
    Integer realPaperId;
    private Integer paperId;
    private String paperName;
    int questionId;
}
