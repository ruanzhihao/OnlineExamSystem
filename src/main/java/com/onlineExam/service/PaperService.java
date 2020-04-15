package com.onlineExam.service;

import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaperService {
    List<Major>  getAllMajor();
    List<Course> getAllCourse();

    List<EasyClass> getAllEasyClass();
    int addPaperService(String paperName, String paperClass, int questionNumber, int answerTime, int sumQuestionScore, int majorId);
    List<Paper> getAllPaper();
    int deletePaperById(@Param("paperId") Integer paperId);
    int updatePaper(@Param("paper") Paper paper);
    Paper findPaperById(@Param("paperId") Integer paperId);
    List<Paper> findPaperByMajorId(@Param("majorId") Integer majorId);
    int updatePaperScore(Map<String, Object> map);
    int updatePaperQuestionNumber(Map<String, Object> map);
    List<PapQuestion> getPapQues(@Param("paperId") Integer paperId);
    int  findSumquestionScore(@Param("paperId") Integer paperId);
    int updatePaperScoreSimpl(Map<String, Object> map);
    int updatePaperQuestionNumber1Simpl(Map<String, Object> map);
}
