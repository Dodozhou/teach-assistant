package com.yuanxueba.teachassistant.dao;

import com.yuanxueba.teachassistant.entity.CommentRecord;
import com.yuanxueba.teachassistant.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRecordDao extends JpaRepository<CommentRecord,Long> {
    //查询留言
    @Query(value = "SELECT * FROM t_comments limit ?1,?2",nativeQuery = true)
    List<CommentRecord> findCommentsLimited(int offset, int limit);
}
