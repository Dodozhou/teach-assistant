package com.yuanxueba.teachassistant.dao;

import com.yuanxueba.teachassistant.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRecordDao extends JpaRepository<ExamRecord,Long> {

}
