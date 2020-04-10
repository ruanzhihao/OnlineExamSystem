package com.onlineExam.mapper;

import com.onlineExam.domain.EasyClass;
import com.onlineExam.domain.Major;
import com.onlineExam.domain.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaperMapper {
       List<Major>  getAllMajor();
       List<EasyClass> getAllEasyClass();
       int addPaper(@Param("paperName") String paperName, @Param("paperClass") String paperClass,
                    @Param("questionNumber") int questionNumber, @Param("answerTime") int answerTime,
                    @Param("sumQuestionScore") int sumQuestionScore, @Param("majorId") int majorId);
       List<Paper> getAllPaper();
       int deletePaperById(@Param("paperId") Integer paperId);
       int updatePaper(Paper paper);
       Paper findPaperById(@Param("paperId") Integer paperId);
      List<Paper>  findPaperByMajorId(@Param("majorId") Integer majorId);
}
