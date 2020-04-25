package com.onlineExam.service.Impl;

import com.onlineExam.domain.Depart;
import com.onlineExam.domain.ExamState;
import com.onlineExam.domain.Paper;
import com.onlineExam.domain.ReleaseExam;
import com.onlineExam.mapper.ReleseExamMapper;
import com.onlineExam.service.ReleaseExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseExamServiceImpl implements ReleaseExamService {
    @Autowired
    private ReleseExamMapper releseExamMapper;

    @Override
    public int releaseExam(Integer departId, Integer majorId, Integer courseId, String beginTime, Integer authorId, Integer paperId) {
        return releseExamMapper.releaseExam(departId ,majorId,courseId,beginTime,authorId,paperId);
    }
    @Override
    public int getJoinReleseClassCount(String stuClazz, Integer releaseExamId) {
        return releseExamMapper.getJoinReleseClassCount(stuClazz,releaseExamId);
    }

    @Override
    public int getAvg(String stuClazz, Integer releaseExamId) {
        return releseExamMapper.getAvg(stuClazz,releaseExamId);
    }

    @Override
    public List<String> getJoinReleseClass(Integer releaseExamId) {
        return releseExamMapper.getJoinReleseClass(releaseExamId);
    }
    @Override
    public List<Paper> getPaperName() {
        return  releseExamMapper.getPaperName();
    }

    @Override
    public List<ReleaseExam> getReleaseInfoByStateId(Integer examStateId) {
        return releseExamMapper.getReleaseInfoByStateId(examStateId);
    }

    @Override
    public int updateReleaseExam(Integer releaseExamId, Integer departId, Integer majorId, Integer courseId, String beginTime, Integer authorId, Integer paperId) {
        return releseExamMapper.updateReleaseExam(releaseExamId,departId,majorId,courseId,beginTime,authorId,paperId);
    }

    @Override
    public ReleaseExam getReleaseExamById(Integer releaseExamId) {
        return releseExamMapper.getReleaseExamById(releaseExamId);
    }

    @Override
    public int deleteReleaseInfoById(Integer releaseExamId) {
        return releseExamMapper.deleteReleaseInfoById(releaseExamId);
    }

    @Override
    public List<ExamState> getAllState() {
        return releseExamMapper.getAllState();
    }

    @Override
    public List<ReleaseExam> getAllReleaseInfo() {
        return releseExamMapper.getAllReleaseInfo();
    }

    @Override
    public List<Depart> getAllDepart() {
        return releseExamMapper.getAllDepart();
    }
}
