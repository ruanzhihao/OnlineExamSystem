package com.onlineExam.domain;

import lombok.Data;

@Data
public class Message {
    private Long id;
    private String sendername;
    private String  head;
    private String content;
    private String createtime;
    private String sturead;
    private String tearead;
}
