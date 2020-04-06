package com.onlineExam.mapper;

import com.onlineExam.domain.Question;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionMapper {

    List<Question> getAllQuestion();//获取所有题目信息
}
