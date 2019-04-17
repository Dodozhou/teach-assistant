package com.yuanxueba.teachassistant.dao;

import com.yuanxueba.teachassistant.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Long> {

    //查询题库
    @Query(value = "SELECT * FROM t_question limit ?1,?2",nativeQuery = true)
    List<Question> findQuestionLimited(int offset, int limit);
}
