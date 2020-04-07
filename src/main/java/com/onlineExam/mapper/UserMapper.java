package com.onlineExam.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    @Select("select * from t_user where uid=#{uid} and password=#{password}")
    public String login(@Param("uid") String uid, @Param("password") String password);

     /*int addUser(User user);
      User selectUser(@Param("name") String name);*/
}
