package com.onlineExam.mapper;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface PaperMapper {
       List<Major>  getAllMajor();
       List<EasyClass> getAllEasyClass();
       List<Course> getAllCourse();
       int addPaper(@Param("paperName") String paperName, @Param("paperClass") String paperClass,
                    @Param("questionNumber") int questionNumber, @Param("answerTime") int answerTime,
                    @Param("sumQuestionScore") int sumQuestionScore, @Param("majorId") int majorId);
       List<Paper> getAllPaper();
       int deletePaperById(@Param("paperId") Integer paperId);
       int updatePaper(Paper paper);
       Paper findPaperById(@Param("paperId") Integer paperId);
      List<Paper>  findPaperByMajorId(@Param("majorId") Integer majorId);
      int updatePaperScore(Map<String, Object> map);
      int updatePaperQuestionNumber(Map<String, Object> map);
      //查询对应试卷的试题
      List<PapQuestion> getPapQues(@Param("paperId") Integer paperId);
      //查询试卷成绩
      int  findSumquestionScore(@Param("paperId") Integer paperId);
      int updatePaperScoreSimpl(Map<String, Object> map);
      int updatePaperQuestionNumber1Simpl(Map<String, Object> map);
}
