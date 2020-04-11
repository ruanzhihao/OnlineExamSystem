package com.onlineExam.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminMapper {

    public String login(@Param("username") String username, @Param("password") String password);

}
