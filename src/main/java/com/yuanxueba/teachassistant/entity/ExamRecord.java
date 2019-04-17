package com.yuanxueba.teachassistant.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_exam_record")
public class ExamRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "考试者账号不能为空")
    @Size(max = 64,message = "账号长度不能超过64位")
    private String examinee; //考试者账号

    @NotNull(message = "试题编号集合不能为空")
    @Size(max = 256,message = "实体编号集合长度不能大于256")
    private String questionsList;  //试题编号集合


    @Size(max = 256,message = "考生答案集合不能大于128")
    private String answersList;    //考生答案集合

    @Size(min = 0,max = 100,message = "分数必须在0~100之间")
    private Integer score;          //分数
    private String beginTime;      //考试时间
    private String endTime;       //考试结束时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExaminee() {
        return examinee;
    }

    public void setExaminee(String examinee) {
        this.examinee = examinee;
    }

    public String getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(String questionsList) {
        this.questionsList = questionsList;
    }

    public String getAnswersList() {
        return answersList;
    }

    public void setAnswersList(String answersList) {
        this.answersList = answersList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
