package com.onlineExam.service.Impl;

import com.onlineExam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AdminService adminService;

    @Override
    public String login(String username, String password) {
        return adminService.login(username,password);
    }
}
