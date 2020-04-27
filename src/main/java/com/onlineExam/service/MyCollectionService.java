package com.onlineExam.service;

import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.MyCollection;

import java.util.List;

public interface MyCollectionService {

    PageInfo getList(Integer pageNum, Integer pageSize, Integer userId);

    List<MyCollection> getList(Integer userId);

    int add(MyCollection myCollection);

    int edit(MyCollection myCollection);

    int del(Integer id);

    MyCollection get(Integer id);
}
