package com.yuanxueba.teachassistant.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL) //空值的序列化支持
@Table(name = "t_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;     //唯一ID

    @NotNull(message = "title不能为空")
    @Size(max = 128,message = "title长度不能超过128位")
    private String title; //资料标题

    @NotNull(message = "link不能为空")
    @Size(max = 1024,message = "link长度不能超过1024位")
    private String link;  //资料链接

    @Size(max = 512,message = "description长度不能超过512位")
    private String description; //资料描述

    @NotNull(message = "uploader不能为空")
    @Size(max = 64,message = "uploader长度不能超过64位")
    private String uploader;  //资料上传者

    private String upTime;   //上传时间

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}
