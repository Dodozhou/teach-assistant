package com.yuanxueba.teachassistant.controller;

import com.yuanxueba.teachassistant.entity.LearnRecord;
import com.yuanxueba.teachassistant.entity.Material;
import com.yuanxueba.teachassistant.entity.Response;
import com.yuanxueba.teachassistant.service.LearnService;
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
@RequestMapping("/learn")
public class LearnController {
    @Autowired
    LearnService learnService;

    @ApiOperation(value = "学习记录查询接口",notes = "用于查询指定用户学习记录",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="learner",value="学习者账号，即当前用户账号",required=true,dataType = "String"),
            @ApiImplicitParam(name="offset",value="偏移量，即从第几条记录开始查",required=true,dataType = "Integer"),
            @ApiImplicitParam(name="limit",value="查询的条数",required=true,dataType = "Integer")
    })
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response<List<LearnRecord>> get(@RequestParam("learner") String learner,
                                        @RequestParam(name = "offset") Integer offset,
                                        @Range(min = 1,max = 50,message = "最少查询1条，最多查询50条") @RequestParam(name = "limit") Integer limit){
        //查询并返回
        List<LearnRecord> learnRecords = learnService.getByLearner(learner,offset,limit);

        return new Response<>(0,"query success",learnRecords);
    }

    @ApiOperation(value = "学习记录添加接口",notes = "用于添加学习记录",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="material_id",value="资料名称",required=true,dataType = "Long"),
            @ApiImplicitParam(name="learner",value="学习者账号，即当前用户账号",required=true,dataType="String")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response<Map<String,Long>> add(@RequestBody @Valid LearnRecord learnRecord){
        Map<String,Long> map = new HashMap<>();
        //数据装填
        learnRecord.setLearnTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //数据保存
        learnService.add(learnRecord);

        //返回记录ID
        map.put("id",learnRecord.getId());

        return new Response<>(0,"add success",map);
    }

}
