package com.onlineExam.service;

import com.onlineExam.domain.EasyClass;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperService {
    List<Major>  getAllMajor();
    List<EasyClass> getAllEasyClass();
    int addPaperService(String paperName, String paperClass, int questionNumber, int answerTime, int sumQuestionScore, int majorId);
    List<Paper> getAllPaper();
    int deletePaperById(@Param("paperId") Integer paperId);
    int updatePaper(@Param("paper") Paper paper);
    Paper findPaperById(@Param("paperId") Integer paperId);
    List<Paper> findPaperByMajorId(@Param("majorId") Integer majorId);
}
