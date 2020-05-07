package com.onlineExam.service.Impl;

import com.onlineExam.domain.FileEntity;
import com.onlineExam.mapper.FileMapper;
import com.onlineExam.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Override
    public int addUrl(FileEntity file) {
        return fileMapper.addUrl(file);
    }

    @Override
    public List<FileEntity> fileList() {
        return fileMapper.fileList();
    }

    @Override
    public int removeFile(Integer id) {
        return fileMapper.removeFile(id);
    }
    @Override
    public int stuState(Integer id) {
        return fileMapper.stuState(id);
    }
}
