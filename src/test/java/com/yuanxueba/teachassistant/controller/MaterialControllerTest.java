package com.yuanxueba.teachassistant.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MaterialControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getMaterials() throws Exception {
        RequestBuilder requestBuilder = get("/material/get")
                .param("offset","2")
                .param("limit","2");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("query success"));
    }

    @Test
    public void getById() throws Exception {
        RequestBuilder requestBuilder = get("/material/getById")
                .param("materialId","2");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("query success"));
    }

    @Test
    @Transactional
    public void addMaterial() throws Exception {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("title","Unix环境高级编程.doc");
        jsonParam.put("link","https://raw.githubusercontent.com/Dodozhou/ImageCDNRepo/master/unix/unix-framework.png");
        jsonParam.put("uploader","8795121033@qq.com");
        RequestBuilder requestBuilder = post("/material/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam.toJSONString());
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("add success"));

    }

    @Test
    @Transactional
    public void deleteMaterial() throws Exception {
        RequestBuilder requestBuilder = get("/material/delete")
                .param("material_id","3");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("delete success"));
    }
}