package com.onlineExam.service;

import com.onlineExam.domain.Message;

import java.util.List;

public interface MessageService {
    public boolean addMessage(Message message);

    public List<Message> findMessage();

    public boolean updateStuRead(Long id);
    public boolean updateTeaRead(Long id);

}
