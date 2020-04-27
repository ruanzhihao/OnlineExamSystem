package com.onlineExam.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Clazz {
    private Integer clazzId;

    private String clazzName;

    private Integer majorId;

    private String majorName;

    private Integer departId;

    private String departName;
}