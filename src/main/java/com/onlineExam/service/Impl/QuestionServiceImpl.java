package com.onlineExam.service.Impl;

import com.onlineExam.domain.Question;
import com.onlineExam.mapper.QuestionMapper;
import com.onlineExam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllQuestion() {
        return questionMapper.getAllQuestion();
    }
}
