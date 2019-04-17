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
public class CommentControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getComments() throws Exception {
        RequestBuilder requestBuilder = get("/comment/get")
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
    public void addComment() throws Exception {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("commenter","1095121033@qq.com");
        jsonParam.put("contents","留言内容");
        RequestBuilder requestBuilder = post("/comment/add")
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
    public void deleteComment() throws Exception {
        RequestBuilder requestBuilder = get("/comment/delete")
                .param("commentId","1");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("delete success"));
    }

    @Test
    @Transactional
    public void updateComment() throws Exception {
        RequestBuilder requestBuilder = get("/comment/update")
                .param("commentId","1")
                .param("content","更新的留言内容");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("update success"));
    }
}