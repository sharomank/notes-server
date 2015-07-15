package com.sharomank.progress.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class MockMvcUtil {
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final MediaType mediaType;

    public MockMvcUtil(MockMvc mockMvc, MediaType mediaType, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.mediaType = mediaType;
        this.mapper = objectMapper;
    }

    public byte[] json(Object object) throws IOException {
        return mapper.writeValueAsBytes(object);
    }

    public ResultActions doPost(Object obj, String uriTemplate, Object... args) throws Exception {
        return mockMvc.perform(
                post(uriTemplate, args)
                        .contentType(mediaType)
                        .content(json(obj))
                        .accept(mediaType))
                .andDo(print());
    }

    public ResultActions doPut(Object obj, String uriTemplate, Object... args) throws Exception {
        return mockMvc.perform(
                put(uriTemplate, args)
                        .contentType(mediaType)
                        .content(json(obj))
                        .accept(mediaType))
                .andDo(print());
    }

    public ResultActions doGet(String uriTemplate, Object... args) throws Exception {
        return mockMvc.perform(
                get(uriTemplate, args).accept(mediaType))
                .andDo(print());
    }

    public ResultActions doDelete(String template, Object... args) throws Exception {
        return mockMvc.perform(
                delete(template, args).accept(mediaType))
                .andDo(print());
    }
}
