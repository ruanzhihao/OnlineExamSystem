package com.onlineExam.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Depart;
import com.onlineExam.mapper.DepartMapper;
import com.onlineExam.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    @Override
    public PageInfo getList(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Depart> departs = departMapper.selectAllByOption(keyword);
        return new PageInfo(departs);
    }

    @Override
    public List<Depart> getList(String keyword) {
        return departMapper.selectAllByOption(keyword);
    }

    @Override
    public int add(Depart depart) {
        return departMapper.insertSelective(depart);
    }

    @Override
    public int edit(Depart depart) {
        return departMapper.updateByPrimaryKeySelective(depart);
    }

    @Override
    public int del(Integer id) {
        return departMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Depart get(int id) {
        return departMapper.selectByPrimaryKey(id);
    }
}
