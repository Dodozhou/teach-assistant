package com.yuanxueba.teachassistant.service;

import com.yuanxueba.teachassistant.dao.QuestionDao;
import com.yuanxueba.teachassistant.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getQuestions(int offset, int limit){
        return questionDao.findQuestionLimited(offset,limit);
    }

    public Long addQuestion(Question question){
        questionDao.save(question);
        return question.getId();
    }

    public void deleteQuestion(Long questionId){
        questionDao.deleteById(questionId);
    }
}
