package com.onlineExam.domain;

import lombok.Data;

@Data
public class LoginUser {
    private Integer id;
    private  String username;
    private  String password;
    private String roles;
}
