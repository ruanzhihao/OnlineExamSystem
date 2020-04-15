package com.onlineExam.service;

import com.onlineExam.domain.RealPaper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface RealPaperService {
      int addRealPaper(Map<String, Object> map);
    int deletePapQues(@Param("realPaperId") Integer realPaperId);
    int addOnePap(@Param("paperId") Integer paperId,@Param("questionId") Integer questionId);
    RealPaper selectOneUnique(@Param("paperId") Integer paperId, @Param("questionId") Integer questionId);
    int countQuestionNumber(@Param("paperId") Integer paperId);
}
