package com.onlineExam.service;

import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Depart;

import java.util.List;

public interface DepartService {

    PageInfo getList(Integer pageNum, Integer pageSize, String keyword);

    List<Depart> getList(String keyword);

    int add(Depart depart);

    int edit(Depart depart);

    int del(Integer id);

    Depart get(int id);
}
