package com.onlineExam.service.Impl;

import com.onlineExam.domain.Message;
import com.onlineExam.mapper.MessageMapper;
import com.onlineExam.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesssageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean addMessage(Message message) {
        int rs=messageMapper.addMessage(message);
        if (rs==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public List<Message> findMessage() {
        return messageMapper.findMessage();
    }
}
