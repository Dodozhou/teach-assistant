package com.yuanxueba.teachassistant.service;

import com.yuanxueba.teachassistant.dao.CommentRecordDao;
import com.yuanxueba.teachassistant.entity.CommentRecord;
import com.yuanxueba.teachassistant.exception.RecordNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRecordDao commentRecordDao;

    /**
     * 查询留言方法
     * @param offset 起始偏移量
     * @param limit 查询条数
     * @return 留言记录
     */
    public List<CommentRecord> getComments(Integer offset, Integer limit){
        return commentRecordDao.findCommentsLimited(offset,limit);
    }

    /**
     * 添加留言方法
     * @param commentRecord 留言实体
     */
    public void addComment(CommentRecord commentRecord){
        commentRecordDao.save(commentRecord);
    }

    /**
     * 更新留言
     * @param commentId 留言ID
     * @param content 留言内容
     */
    public void updateComment(Long commentId,String content) throws RecordNotExistException {
        CommentRecord record = commentRecordDao.getOne(commentId);
        record.setContents(content);
        record.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        commentRecordDao.save(record);

    }

    /**
     * 删除留言
     * @param commentId 留言ID
     */
    public void deleteComment(Long commentId){
        commentRecordDao.deleteById(commentId);
    }


}
