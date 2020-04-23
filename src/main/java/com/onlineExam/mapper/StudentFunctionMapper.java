package com.onlineExam.mapper;

import com.onlineExam.domain.Paper;

import com.onlineExam.domain.Question;
import com.onlineExam.domain.ReleaseExam;
import com.onlineExam.domain.StuAnswer;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface StudentFunctionMapper {
    //查询对应学生major
    Integer getStuMajor(@Param("username") String username);
    //根据major查询对应专业的考务信息
    List<ReleaseExam> getReleaseByMajor(@Param("majorId") Integer majorId);
    //查询对应的answerTime
    /*Paper getAnswerTime(@Param("paperId") Integer paperId);*/
    //更改状态
    int updateState(@Param("releaseExamId") Integer releaseExamId, @Param("examStateId") Integer examStateId);
    //学生端考务展示
    List<ReleaseExam> selectReleaseTime();
    //展示多选题目
    List<Question> getCheckQuestion(Integer paperId);
    //展示多选题条目
    int getCheckBoxCount(@Param("paperId") Integer paperId);
    //展示多选总分值
    int getCheckBoxQuestionScore(@Param("paperId") Integer paperId);
    //添加考试记录
    int insertExamRecord(Map<Object,Object> recordMap );
    //查询用户考试详情
    List<StuAnswer> useranswerRecord(@Param("paperId")Integer paperId,@Param("releaseExamId") Integer releaseExamId);
    //查询问题答案
    List<Question> questionAnswer(@Param("paperId") Integer paperId);
    int setWrong(@Param("stuAnswerId") Integer stuAnswerId,@Param("isWrong") Integer isWrong );
    //展示多选题目
    List<Question> getRadioQuestion(@Param("paperId") Integer paperId);
    //展示多选题条目
    int getRadioCount(@Param("paperId") Integer paperId);
    //展示多选总分值
    int getRadioQuestionScore(@Param("paperId") Integer paperId);
    //查询答题时间
    int getAnswerTime(@Param("paperId") Integer paperId);

    //考试记录单选题目的详情
    List<StuAnswer> getHistory(@Param("paperId")int paperId,@Param("stuId")int stuId);

    //考试记录多选题目的详情
    List<StuAnswer> getCheckHistory(@Param("paperId")int paperId,@Param("stuId")int stuId);

}
