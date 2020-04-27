package com.onlineExam.service;

import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Clazz;

import java.util.List;

public interface ClazzService {

    PageInfo getList(Integer pageNum, Integer pageSize, Integer departId, Integer majorId, String keyword);

    List<Clazz> getList(Integer departId, Integer majorId, String keyword);

    int add(Clazz clazz);

    int edit(Clazz clazz);

    int del(Integer id);

    Clazz get(Integer id);
}
