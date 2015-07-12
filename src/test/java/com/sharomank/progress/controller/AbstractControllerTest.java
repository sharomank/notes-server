package com.sharomank.progress.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharomank.progress.ProgressApplication;
import com.sharomank.progress.model.BaseModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ProgressApplication.class)
@WebAppConfiguration
public abstract class AbstractControllerTest<T extends BaseModel> {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    protected ObjectMapper objectMapper;
    protected MockMvc mockMvc;
    protected List<T> testItems = new ArrayList<>();
    @Autowired
    private WebApplicationContext webAppContext;

    public static byte[] json(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppContext).build();
    }

    protected abstract String getResourcesUriPath();

    private String getItemUriPath(String id) {
        return getResourcesUriPath() + "/" + id;
    }

    @Test
    public void createItem() throws Exception {
        T testItem = getTestItem();
        testItem.setId(null);
        testItem.setCreated(null);
        mockMvc.perform(
                post(getResourcesUriPath())
                        .content(json(testItem))
                        .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated());
    }

    @Test
    public void getItems() throws Exception {
        mockMvc.perform(
                get(getResourcesUriPath())
                        .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[2].id").exists());
    }

    @Test
    public void getItem() throws Exception {
        String content = mockMvc.perform(
                get(getResourcesUriPath())
                        .accept(APPLICATION_JSON_UTF8))
                .andReturn().getResponse().getContentAsString();

        JavaType resultType = objectMapper.getTypeFactory().constructCollectionType(List.class, getTestItem().getClass());
        List<T> items = objectMapper.readValue(content, resultType);
        items.stream().forEach(item -> {
            try {
                mockMvc.perform(get(getItemUriPath(item.getId())).accept(APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(item.getId())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void itemNotFound() throws Exception {
        mockMvc.perform(
                get(getItemUriPath(UUID.randomUUID().toString()))
                        .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateItem() throws Exception {
        T updateItem = getTestItem();
        updateItem.setName(updateItem.getName() + " - UPDATED");
        mockMvc.perform(
                put(getItemUriPath(updateItem.getId()))
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(json(updateItem)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updateItem.getId())))
                .andExpect(jsonPath("$.name", is(updateItem.getName())));
    }

    @Test
    public void deleteItem() throws Exception {
        mockMvc.perform(
                delete(getItemUriPath(getTestItem().getId()))
                        .accept(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private T getTestItem() {
        Assert.isTrue(testItems.size() > 0, "You should insert test data via repository and after that add them to 'testItems' variable.");
        return testItems.get(0);
    }
}
