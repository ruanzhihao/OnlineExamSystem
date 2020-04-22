package com.onlineExam.mapper;

import com.onlineExam.domain.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {

    public int addMessage(Message message);

    public List<Message> findMessage();
}
