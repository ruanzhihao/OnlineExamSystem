package com.onlineExam.domain;

import lombok.Data;

@Data
public class FileEntity {
    private int id;
    private String title;
    private String createtime;
    private String uploader;
    private String path;
    private String url;
    private String state;

}
