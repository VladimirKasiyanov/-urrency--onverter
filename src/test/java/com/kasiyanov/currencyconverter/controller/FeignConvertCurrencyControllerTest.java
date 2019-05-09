package com.kasiyanov.currencyconverter.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignConvertCurrencyControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void checkConvertEurToUsd() throws Exception {
        MockHttpServletRequestBuilder get = get("/feign/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"from\": \"EUR\", " +
                        "\"to\": \"USD\", " +
                        "\"amount\": \"100\"" +
                        "}");

        mockMvc.perform(get)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("111.9963"));
    }

    @Test
    public void checkConvertRubToEur() throws Exception {
        MockHttpServletRequestBuilder get = get("/feign/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{" +
                        "\"from\": \"RUB\", " +
                        "\"to\": \"EUR\", " +
                        "\"amount\": \"10000\"" +
                        "}");

        mockMvc.perform(get)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("137.5953"));
    }
}