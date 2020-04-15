package com.onlineExam.mapper;

import com.onlineExam.domain.RealPaper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface RealPaperMapper {
      int addRealPaper(Map<String, Object> map);

      int deletePapQues(@Param("realPaperId") Integer realPaperId);
      //查询单个paper
      RealPaper selectOneUnique(@Param("paperId") Integer paperId,@Param("questionId") Integer questionId);
      //单个添加
      int addOnePap(@Param("paperId") Integer paperId,@Param("questionId") Integer questionId);
      int countQuestionNumber(@Param("paperId") Integer paperId);
}
