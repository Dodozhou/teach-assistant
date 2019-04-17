package com.yuanxueba.teachassistant.dao;

import com.yuanxueba.teachassistant.entity.LearnRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRecordDao extends JpaRepository<LearnRecord,Long> {
    //查找特定learner的学习记录
    @Query(value = "SELECT * FROM t_learn_record WHERE learner=?1 ORDER BY learn_time DESC limit ?2,?3",nativeQuery = true)
    List<LearnRecord> findSpecificRecord(String learner,Integer offset,Integer limit);
}
