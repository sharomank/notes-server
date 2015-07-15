package com.sharomank.progress.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sharomank.progress.ProgressApplication;
import com.sharomank.progress.model.BaseModel;
import com.sharomank.progress.util.Constant;
import com.sharomank.progress.util.MockMvcUtil;
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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
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
    protected MockMvcUtil mockMvcUtil;
    protected List<T> testItems = new ArrayList<>();
    @Autowired
    private WebApplicationContext webAppContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webAppContext).build();
        mockMvcUtil = new MockMvcUtil(mockMvc, APPLICATION_JSON_UTF8, objectMapper);
    }

    protected abstract String getUriPath();

    protected abstract T getTestItemForInsert();

    private String getItemUriPath() {
        return getUriPath() + Constant.PATH_VARIABLE_ID;
    }

    @Test
    public void createItem() throws Exception {
        T testItem = getTestItemForInsert();
        mockMvcUtil.doPost(testItem, getUriPath())
                .andExpect(status().isCreated());
    }

    @Test
    public void getItems() throws Exception {
        mockMvcUtil.doGet(getUriPath())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[2].id").exists());
    }

    @Test
    public void getItem() throws Exception {
        String content = mockMvcUtil.doGet(getUriPath())
                .andReturn().getResponse().getContentAsString();

        JavaType resultType = objectMapper.getTypeFactory().constructCollectionType(List.class, getTestItem().getClass());
        List<T> items = objectMapper.readValue(content, resultType);
        items.stream().forEach(item -> {
            try {
                mockMvcUtil.doGet(getItemUriPath(), item.getId())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id", is(item.getId())));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void getItemNotFound() throws Exception {
        mockMvcUtil.doGet(getItemUriPath(), getRandomId())
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateItem() throws Exception {
        T updateItem = getTestItem();
        updateItem.setName(updateItem.getName() + updateItem.toString());
        mockMvcUtil.doPut(updateItem, getItemUriPath(), updateItem.getId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updateItem.getId())))
                .andExpect(jsonPath("$.name", is(updateItem.getName())));
    }

    @Test
    public void updateNotFound() throws Exception {
        mockMvcUtil.doPut(getTestItem(), getItemUriPath(), getRandomId())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteItem() throws Exception {
        mockMvcUtil.doDelete(getItemUriPath(), getTestItem().getId())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteItemNotFound() throws Exception {
        mockMvcUtil.doDelete(getItemUriPath(), getRandomId())
                .andExpect(status().isNotFound());
    }

    protected String getRandomId() {
        return UUID.randomUUID().toString();
    }

    protected T getTestItem() {
        Assert.isTrue(testItems.size() > 0, "You should insert test data via repository and after that add them to 'testItems' variable.");
        return testItems.get(0);
    }
}
