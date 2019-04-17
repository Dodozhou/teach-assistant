package com.yuanxueba.teachassistant.controller;

import com.yuanxueba.teachassistant.entity.Material;
import com.yuanxueba.teachassistant.entity.Question;
import com.yuanxueba.teachassistant.entity.Response;
import com.yuanxueba.teachassistant.service.QuestionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @ApiOperation(value = "试题查询接口",notes = "用于查询所有试题的接口",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="offset",value="偏移量，即从第几条记录开始查",required=true,dataType = "Integer"),
            @ApiImplicitParam(name="limit",value="查询的条数",required=true,dataType = "Integer")
    })
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response<List<Question>> getQuestions(@RequestParam(name = "offset",required = true) Integer offset,
                                 @Range(min = 1,max = 50,message = "最少查询1条，最多查询50条") @RequestParam(name = "limit",required = true) Integer limit){
        //查询并返回
        List<Question> questions = questionService.getQuestions(offset,limit);

        return new Response<>(0,"query success",questions);
    }

    @ApiOperation(value = "试题添加接口",notes = "用于添加试题的接口",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="题目",required=true,dataType = "String"),
            @ApiImplicitParam(name="options",value="选项链接集合",required=true,dataType = "String"),
            @ApiImplicitParam(name="answer",value="答案",required=true,dataType="Integer"),
            @ApiImplicitParam(name="creator",value="创建者账号，即当前用户账号",required=true,dataType="String")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response<Map<String,Long>> addQuestion(@RequestBody @Valid Question question){
        Map<String,Long> map = new HashMap<>();
        //数据装填
        question.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //数据保存
        questionService.addQuestion(question);

        //返回记录ID
        map.put("id",question.getId());

        return new Response<>(0,"add success",map);
    }

    @ApiOperation(value = "试题删除接口",notes = "用于删除特定试题",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="question_id",value="试题的ID",required=true,dataType = "Long")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Response deleteQuestions(@RequestParam(name = "question_id",required = true) Long questionId){
        //执行删除
        questionService.deleteQuestion(questionId);

        return new Response<>(0,"delete success");
    }


}
