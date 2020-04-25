package com.onlineExam.controller.student;

import com.onlineExam.domain.Message;
import com.onlineExam.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StuMessageController {
    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("/stumessage")
    public String findMessage(Model model){
        List<Message> messageList= messageMapper.findMessage();

        model.addAttribute("messageList",messageList);
        return "student/stumessage";
    }
    @RequestMapping("/sturead")
    @Transactional
    public String updateStuRead(Long id){
        int result= messageMapper.updateStuRead(id);
        return "student/stumessage";
    }

}
