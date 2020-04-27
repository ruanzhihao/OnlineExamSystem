package com.onlineExam.mapper;

import com.onlineExam.domain.MyCollection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MyCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyCollection record);

    int insertSelective(MyCollection record);

    MyCollection selectByPrimaryKey(Integer id);

    List<MyCollection> selectAllByOption(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(MyCollection record);

    int updateByPrimaryKey(MyCollection record);
}