package com.onlineExam.mapper;

import com.onlineExam.domain.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {

    //全部已读
    public int stuReadAll();
    public int teaReadAll();
    //添加消息
    public int addMessage(Message message);

    //查看所有消息
    public List<Message> findMessage();

    public int updateStuRead(Long id);
    public int updateTeaRead(Long id);
}
