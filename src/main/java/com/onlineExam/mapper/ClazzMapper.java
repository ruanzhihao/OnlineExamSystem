package com.onlineExam.mapper;

import com.onlineExam.domain.Clazz;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClazzMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Clazz record);

    Clazz selectByPrimaryKey(Integer id);

    List<Clazz> selectAllByOption(@Param("departId") Integer departId,
                                  @Param("majorId") Integer majorId,
                                  @Param("keyword") String keyword);

    int updateByPrimaryKeySelective(Clazz record);
}