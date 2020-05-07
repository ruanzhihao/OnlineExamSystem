package com.onlineExam.service;

import com.onlineExam.domain.FileEntity;

import java.util.List;

public interface FileService {
    public int addUrl(FileEntity file);
    public List<FileEntity> fileList();
    public int removeFile(Integer id);
    public int stuState(Integer id);
}
