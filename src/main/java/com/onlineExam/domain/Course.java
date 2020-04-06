package com.onlineExam.domain;

import lombok.Data;

/**
 * 课程
 */
@Data
public class Course {
    private Integer courseId;
    private String courseName;
    private Integer majorId;
    private String majorName;
    private Integer departId;
    private  String departName;
}
