package com.onlineExam.domain;

import lombok.Data;

/**
 * 专业方向
 */
@Data
public class Major {
    private Integer majorId;
    private String majorName;
    private Integer departId;
    private  String departName;
}
