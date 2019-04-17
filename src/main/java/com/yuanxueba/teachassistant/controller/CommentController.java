package com.yuanxueba.teachassistant.controller;

import com.yuanxueba.teachassistant.entity.CommentRecord;
import com.yuanxueba.teachassistant.entity.Material;
import com.yuanxueba.teachassistant.entity.Response;
import com.yuanxueba.teachassistant.exception.RecordNotExistException;
import com.yuanxueba.teachassistant.service.CommentService;
import com.yuanxueba.teachassistant.service.MaterialService;
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
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "全量留言查询接口",notes = "用于分段查询所有留言",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="offset",value="偏移量，即从第几条记录开始查",required=true,dataType = "Integer"),
            @ApiImplicitParam(name="limit",value="查询的条数",required=true,dataType = "Integer")
    })
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response<List<CommentRecord>> getComments(@RequestParam(name = "offset") Integer offset,
                                                 @Range(min = 1,max = 50,message = "最少查询1条，最多查询50条") @RequestParam(name = "limit") Integer limit){
        //查询并返回
        List<CommentRecord> comments = commentService.getComments(offset,limit);

        return new Response<>(0,"query success",comments);
    }


    @ApiOperation(value = "留言添加接口",notes = "用于添加留言",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="commenter",value="留言者账号",required=true,dataType = "String"),
            @ApiImplicitParam(name="contents",value="留言内容",required=true,dataType="String")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response<Map<String,Long>> addComment(@RequestBody @Valid CommentRecord record){
        Map<String,Long> map = new HashMap<>();
        //数据装填
        record.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //数据保存
        commentService.addComment(record);
        //返回记录ID
        map.put("id",record.getId());

        return new Response<>(0,"add success",map);
    }

    @ApiOperation(value = "留言删除接口",notes = "用于删除留言",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="commentId",value="留言的ID",required=true,dataType = "Long")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Response deleteMaterial(@RequestParam(name = "commentId") Long commentId){
        //执行删除
        commentService.deleteComment(commentId);

        return new Response(0,"delete success");
    }

    @ApiOperation(value = "留言更改接口",notes = "用于更改留言内容，自动更新留言时间",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="commentId",value="留言的ID",required=true,dataType = "Long"),
            @ApiImplicitParam(name="content",value="留言内容",required=true,dataType = "String")
    })
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Response update(@RequestParam(name = "commentId") Long commentId,
                           @RequestParam(name = "content") String content) throws RecordNotExistException {
        commentService.updateComment(commentId,content);

        return new Response(0,"update success");
    }


}
