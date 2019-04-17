package com.yuanxueba.teachassistant.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_comments")
public class CommentRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "留言者账号不能为空")
    @Size(max = 64,message = "留言者账号长度不能超过64位")
    private String commenter; //留言者账号

    @NotNull(message = "留言内容不能为空")
    @Size(max = 1024,message = "留言的最大长度不能超过1024个字符")
    private String contents; //留言内容

    private String commentTime; //留言时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommenter() {
        return commenter;
    }

    public void setCommenter(String commenter) {
        this.commenter = commenter;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
