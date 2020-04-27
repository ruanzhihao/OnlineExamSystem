package com.onlineExam.service;

import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Major;

import java.util.List;

public interface MajorService {

    PageInfo getList(Integer pageNum, Integer pageSize, Integer departId, String keyword);

    List<Major> getList(Integer departId, String keyword);

    int add(Major major);

    int edit(Major major);

    int del(Integer id);

    Major get(Integer id);
}
