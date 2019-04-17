package com.yuanxueba.teachassistant.service;

import com.yuanxueba.teachassistant.dao.LearnRecordDao;
import com.yuanxueba.teachassistant.entity.LearnRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnService {
    @Autowired
    LearnRecordDao learnRecordDao;

    /**
     * 添加学习记录
     * @param record 学习记录实体
     */
    public void add(LearnRecord record){
        learnRecordDao.save(record);
    }

    /**
     * 查找特定用户的学习记录
     * @param learner 学习者账号
     * @return 该学习者的所有学习记录
     */
    public List<LearnRecord> getByLearner(String learner,Integer offset, Integer limit){
        return learnRecordDao.findSpecificRecord(learner,offset,limit);
    }


}
