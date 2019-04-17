package com.yuanxueba.teachassistant.controller;

import com.yuanxueba.teachassistant.entity.Material;
import com.yuanxueba.teachassistant.entity.Response;
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
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    MaterialService materialService;

    @ApiOperation(value = "资料（文件、视频等）全量查询接口",notes = "用于查询所有资料的信息",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="offset",value="偏移量，即从第几条记录开始查",required=true,dataType = "Integer"),
            @ApiImplicitParam(name="limit",value="查询的条数",required=true,dataType = "Integer")
    })
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response<List<Material>> getMaterials(@RequestParam(name = "offset") Integer offset,
                                                 @Range(min = 1,max = 50,message = "最少查询1条，最多查询50条") @RequestParam(name = "limit") Integer limit){
        //查询并返回
        List<Material> materials = materialService.getMaterials(offset,limit);

        return new Response<>(0,"query success",materials);
    }

    @ApiOperation(value = "特定资料查询接口",notes = "用于查询特定一份资料的信息",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="materialId",value="资料的ID",required=true,dataType = "Long")
    })
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public Response<Material> getMaterialById(@RequestParam(name = "materialId") Long materialId){
        //查询并返回
        Material material = materialService.getById(materialId);

        return new Response<>(0,"query success",material);
    }

    @ApiOperation(value = "资料（文件、视频等）添加接口",notes = "用于添加资料（文件、视频等）",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title",value="资料名称",required=true,dataType = "String"),
            @ApiImplicitParam(name="link",value="资料的链接",required=true,dataType = "String"),
            @ApiImplicitParam(name="description",value="资料的简介",required=false,dataType="String"),
            @ApiImplicitParam(name="uploader",value="上传者账号，即当前用户账号",required=true,dataType="String")
    })
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Response<Map<String,Long>> addMaterial(@RequestBody @Valid Material material){
        Map<String,Long> map = new HashMap<>();
        //数据装填
        material.setUpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        //数据保存
        materialService.add(material);

        //返回记录ID
        map.put("id",material.getId());

        return new Response<>(0,"add success",map);
    }

    @ApiOperation(value = "资料（文件、视频等）删除接口",notes = "用于删除特定资料（文件、视频等）",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name="material_id",value="试题的ID",required=true,dataType = "Long")
    })
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Response<Object> deleteMaterial(@RequestParam(name = "material_id") Long materialId){
        //执行删除
        materialService.delete(materialId);

        return new Response<>(0,"delete success");
    }


}
