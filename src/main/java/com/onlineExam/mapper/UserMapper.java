package com.onlineExam.mapper;


import com.onlineExam.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
@Component
public interface UserMapper {
     int addUser(User user);
      User selectUser(@Param("name") String name);
}
