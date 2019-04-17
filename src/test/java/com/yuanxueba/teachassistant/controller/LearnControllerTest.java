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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LearnControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getLearnRecord() throws Exception {
        RequestBuilder requestBuilder = get("/learn/get")
                .param("learner","1095121033@qq.com")
                .param("offset","0")
                .param("limit","2");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("query success"));
    }

    @Test
    @Transactional
    public void addLearnRecord() throws Exception {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("materialId","3");
        jsonParam.put("learner","1095121033@qq.com");
        RequestBuilder requestBuilder = post("/learn/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam.toJSONString());
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("add success"));

    }

}