package com.onlineExam.mapper;

import com.onlineExam.domain.Major;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    List<Major> selectAllByOption(@Param("departId") Integer departId,
                                  @Param("keyword") String keyword);

    int updateByPrimaryKeySelective(Major record);
}