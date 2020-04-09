package com.onlineExam.domain;

import lombok.Data;


@Data
public class Teacher {
    private Integer teacherid;
    private String username;
    private String teachername;
    private String teacherpassword;
    private String teacherphoneNumber;
    private String teacheremail;
    private int majorId;
    private String majorName;
    private int departId;
    private String departName;
    private int stateId;
    private String state;

}
