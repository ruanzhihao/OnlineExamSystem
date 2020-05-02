package com.onlineExam.service;

import com.onlineExam.domain.Message;

import java.util.List;

public interface MessageService {

    public int editMessage(Message message);
    public boolean deleteMessage(Long id);
    public Message findMessageById(Long id);
    //全部已读
    public boolean stuReadAll();
    public boolean teaReadAll();

    public boolean addMessage(Message message);

    public List<Message> findMessage();

    public boolean updateStuRead(Long id);
    public boolean updateTeaRead(Long id);

}
