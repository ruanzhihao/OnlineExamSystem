package com.onlineExam.mapper;

import com.onlineExam.domain.Depart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DepartMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(Integer id);

    List<Depart> selectAllByOption(@Param("keyword") String keyword);

    int updateByPrimaryKeySelective(Depart record);
}