package com.onlineExam.service;

import org.apache.ibatis.annotations.Param;

public interface AdminService {

    public String login(@Param("username") String username, @Param("password") String password);

}
