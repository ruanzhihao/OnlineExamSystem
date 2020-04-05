package com.onlineExam.service.Impl;

import com.onlineExam.domain.User;
import com.onlineExam.mapper.UserMapper;
import com.onlineExam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public int addUser(User user){
        int row=userMapper.addUser(user);
        return row;
    }
    public User selectUser(String name){
        User user=userMapper.selectUser(name);
        return  user;
    }
}
