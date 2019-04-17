package com.yuanxueba.teachassistant.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) //空值的序列化支持
@Table(name = "t_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  //主键

    @NotNull(message = "title不能为空")
    @Size(max = 512,message = "title长度不能超过512位")
    private String title;  //题干

    @NotNull(message = "options不能为空")
    @Size(max = 1024,message = "options长度不能超过1024位")
    private String options; //选项集合，用分号分隔

    @NotNull(message = "answer不能为空")
    @Size(max = 128,message = "answer长度不能超过128位")
    private String answer; //答案

    @NotNull(message = "creator不能为空")
    @Size(max = 64,message = "creator长度不能超过64位")
    private String creator; //创建者

    private String createTime; //创建时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
