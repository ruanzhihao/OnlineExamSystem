package com.onlineExam.service.Impl;


import com.onlineExam.domain.ReleaseExam;
import com.onlineExam.mapper.StudentFunctionMapper;
import com.onlineExam.service.StudentFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentFunctionServiceImpl implements StudentFunctionService {
    @Autowired
    private StudentFunctionMapper studentFunctionMapper;

    @Override
    public Integer getStuMajor(String username) {
        return studentFunctionMapper.getStuMajor(username);
    }

    @Override
    public List<ReleaseExam> getReleaseByMajor(Integer majorId) {
        return studentFunctionMapper.getReleaseByMajor(majorId);
    }


    @Override
    public List<ReleaseExam> selectReleaseTime() {
        return studentFunctionMapper.selectReleaseTime();
    }

    @Override
    public int updateState(Integer releaseExamId, Integer examStateId) {
        return studentFunctionMapper.updateState(releaseExamId,examStateId);
    }
}
