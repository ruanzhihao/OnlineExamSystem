package com.onlineExam.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Clazz;
import com.onlineExam.mapper.ClazzMapper;
import com.onlineExam.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageInfo getList(Integer pageNum, Integer pageSize, Integer departId, Integer majorId, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Clazz> clazzes = clazzMapper.selectAllByOption(departId, majorId, keyword);
        return new PageInfo(clazzes);
    }

    @Override
    public List<Clazz> getList(Integer departId, Integer majorId, String keyword) {
        return clazzMapper.selectAllByOption(departId, majorId, keyword);
    }

    @Override
    public int add(Clazz clazz) {
        return clazzMapper.insertSelective(clazz);
    }

    @Override
    public int edit(Clazz clazz) {
        return clazzMapper.updateByPrimaryKeySelective(clazz);
    }

    @Override
    public int del(Integer id) {
        return clazzMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Clazz get(Integer id) {
        return clazzMapper.selectByPrimaryKey(id);
    }
}
