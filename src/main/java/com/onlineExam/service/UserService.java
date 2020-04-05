package com.onlineExam.service;

import com.onlineExam.domain.User;

public interface UserService {
    public int addUser(User user);
    public User selectUser(String name);
}
