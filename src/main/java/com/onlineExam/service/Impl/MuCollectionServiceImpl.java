package com.onlineExam.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.onlineExam.domain.MyCollection;
import com.onlineExam.mapper.MyCollectionMapper;
import com.onlineExam.mapper.QuestionMapper;
import com.onlineExam.service.MyCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuCollectionServiceImpl implements MyCollectionService {

    @Autowired
    private MyCollectionMapper myCollectionMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public PageInfo getList(Integer pageNum, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<MyCollection> myCollections = myCollectionMapper.selectAllByOption(userId);
        for (MyCollection myCollection:myCollections) {
            myCollection.setQuestion(questionMapper.showQuestion(myCollection.getQuestionid()));
        }
        return new PageInfo(myCollections);
    }

    @Override
    public List<MyCollection> getList(Integer userId) {
        List<MyCollection> myCollections = myCollectionMapper.selectAllByOption(userId);
        for (MyCollection myCollection:myCollections) {
            myCollection.setQuestion(questionMapper.showQuestion(myCollection.getQuestionid()));
        }
        return myCollections;
    }

    @Override
    public int add(MyCollection myCollection) {
        return myCollectionMapper.insertSelective(myCollection);
    }

    @Override
    public int edit(MyCollection myCollection) {
        return myCollectionMapper.updateByPrimaryKeySelective(myCollection);
    }

    @Override
    public int del(Integer id) {
        return myCollectionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public MyCollection get(Integer id) {
        return myCollectionMapper.selectByPrimaryKey(id);
    }
}
