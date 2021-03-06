package com.onlineExam.domain;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String username;
    private Integer stuid;
    private String stuname;
    private String stupassword;
    private String stuphonenumber;
    private String stuemail;
    private int majorId;
    private String majorName;
    private int departId;
    private String departName;
    private int stateId;
    private String state;
    private int clazzId;
    private String clazzName;
    private String headerurl;

    private String examName;//新添加
    private int examScore;//新添加
}
