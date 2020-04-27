package com.onlineExam.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.Major;
import com.onlineExam.mapper.MajorMapper;
import com.onlineExam.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public PageInfo getList(Integer pageNum, Integer pageSize, Integer departId, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Major> majors = majorMapper.selectAllByOption(departId, keyword);
        return new PageInfo(majors);
    }

    @Override
    public List<Major> getList(Integer departId, String keyword) {
        return majorMapper.selectAllByOption(departId, keyword);
    }

    @Override
    public int add(Major major) {
        return majorMapper.insertSelective(major);
    }

    @Override
    public int edit(Major major) {
        return majorMapper.updateByPrimaryKeySelective(major);
    }

    @Override
    public int del(Integer id) {
        return majorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Major get(Integer id) {
        return majorMapper.selectByPrimaryKey(id);
    }
}
