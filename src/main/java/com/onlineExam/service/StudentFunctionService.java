package com.onlineExam.service;


import com.onlineExam.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentFunctionService {
    Integer getStuMajor(@Param("username") String username);
    List<ReleaseExam> getReleaseByMajor(@Param("majorId") Integer majorId);
    int updateState(@Param("releaseExamId") Integer releaseExamId, @Param("examStateId") Integer examStateId);
    List<ReleaseExam> selectReleaseTime();
    //展示多选题目
    List<Question> getCheckQuestion(@Param("paperId") Integer paperId);
    //展示多选题条目
    int getCheckBoxCount(@Param("paperId") Integer paperId);
    //展示多选总分值
    int getCheckBoxQuestionScore(@Param("paperId") Integer paperId);
    int insertExamRecord(Map<Object,Object> recordMap );
    List<StuAnswer> useranswerRecord(@Param("paperId")Integer paperId,@Param("releaseExamId") Integer releaseExamId);
    List<Question> questionAnswer(@Param("paperId") Integer paperId);
    int setWrong(@Param("stuAnswerId") Integer stuAnswerId,@Param("isWrong") Integer isWrong );
    //展示多选题目
    List<Question> getRadioQuestion(@Param("paperId") Integer paperId);
    //展示多选题条目
    int getRadioCount(@Param("paperId") Integer paperId);
    //展示多选总分值
    int getRadioQuestionScore(@Param("paperId") Integer paperId);
    int getAnswerTime(@Param("paperId") Integer paperId);
    String getExamInfoByStuId(@Param("stuId") Integer stuId, @Param("releaseExamId") Integer releseExamId);
    //考试记录查看详情
    List<StuAnswer> getHistory(int paperId,int stuId);
    //考试记录多选题目的详情
    List<StuAnswer> getCheckHistory(int paperId, int stuId);

    List<Question> getShortAnswer(@Param("paperId") Integer paperId);

    int getShortAnswerCount(@Param("paperId") Integer paperId);

    int getShortAnswerScore(@Param("paperId") Integer paperId);

    List<Question> shortAnswerIdInPaper(@Param("paperId") Integer paperId);

    int updateShortAnswerState(Map<Object, Object> shortAnswers);
    List<ReleaseExam> getTeaReleaseExam(@Param("authorId") int authorId);
    int waitCheckNumber(@Param("releaseExamId") Integer releaseExamId);
    List<ReleaseExam> getUncheck(@Param("releaseExamId") Integer releaseExamId);
    List<StuAnswer> stuShortAnswer(@Param("releaseExamId") Integer releaseExamId,@Param("stuId") Integer stuId);
    int updateShortAnswer(Map<String,Object> teaCheckScoreMap);
    int updateIsCheck(@Param("releaseExamId") Integer releaseExamId,@Param("stuId") Integer stuId);
    int getSumScore(Map<String,Object> scoreMap);
    List<Question> getShortAnsScoreIntoPap(@Param("releaseExamId") Integer releaseExamId);

    //评论存储
    int insertComment(@Param("studyId") int studyId, @Param("tgStuId") int tgStuId, @Param("stuId") int stuId, @Param("comments")String comments,@Param("publicTime")String publicTime);
    //获取学生资料
    Student studentById(@Param("stuId") int stuId);
    //评论显示
    List<Comment> showComment(@Param("studyId")int studyId,@Param("tgStuId")int tgStuId,@Param("stuId")int stuId);
}
