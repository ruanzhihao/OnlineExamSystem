package com.onlineExam.mapper;

import com.onlineExam.domain.FileEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FileMapper {

    public int addUrl(FileEntity file);
    public List<FileEntity> fileList();
    public int removeFile(Integer id);
    public int stuState(Integer id);
}
