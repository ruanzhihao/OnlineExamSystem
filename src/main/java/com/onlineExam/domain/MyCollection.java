package com.onlineExam.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
public class MyCollection {
    private Integer id;

    private Integer questionid;

    private Question question;

    private Integer userid;

    private Date createtime;

    public MyCollection() {
    }

    public MyCollection(Integer id, Integer questionid, Question question, Integer userid, Date createtime) {
        this.id = id;
        this.questionid = questionid;
        this.question = question;
        this.userid = userid;
        this.createtime = createtime;
    }

    public MyCollection(Integer id, Integer questionid, Integer userid, Date createtime) {
        this.id = id;
        this.questionid = questionid;
        this.userid = userid;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionid() {
        return questionid;
    }

    public void setQuestionid(Integer questionid) {
        this.questionid = questionid;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}