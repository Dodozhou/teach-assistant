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
public class QuestionControllerTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getQuestions() throws Exception {
        RequestBuilder requestBuilder = get("/question/get")
                .param("offset","2")
                .param("limit","2");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("query success"));
    }

    @Test
    @Transactional
    public void addQuestion() throws Exception {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("title","请找出下图操作中有错误的一项：");
        jsonParam.put("options","https://raw.githubusercontent.com/Dodozhou/ImageCDNRepo/master/unix/unix-framework.png;https://raw.githubusercontent.com/Dodozhou/ImageCDNRepo/master/unix/unix-framework.png;https://raw.githubusercontent.com/Dodozhou/ImageCDNRepo/master/unix/unix-framework.png");
        jsonParam.put("answer","D");
        jsonParam.put("creator","230121033@qq.com");
        RequestBuilder requestBuilder = post("/question/add")
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
    public void deleteQuestions() throws Exception {
        RequestBuilder requestBuilder = get("/question/delete")
                .param("question_id","3");
        mvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retCode").value(0))
                .andExpect(jsonPath("$.msg").value("delete success"));
    }
}