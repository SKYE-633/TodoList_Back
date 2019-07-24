package com.dyt;

import com.dyt.pojo.Todo;
import com.dyt.responsitory.TodoListResponsitory;
import com.dyt.service.TodoListService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoListService todoListService;

    @Test
    public void should_return_todo_when_add() throws Exception {
        //given
        MvcResult mvcResult = mockMvc.perform(post("/todoList").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "  \"value\": \"101\",\n" +
                        "  \"status\": true\n" +
                        "}")).andReturn();
        //when
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONObject jsonObject = new JSONObject(contentAsString);
        String value = jsonObject.getString("value");
        //then
        Assertions.assertEquals("101",value);
    }

    @Test
    public void should_return_todoList_when_find_all() throws Exception {
        //give
        todoListService.addTodo(new Todo("500", true));
        todoListService.addTodo(new Todo("50", true));
        todoListService.addTodo(new Todo("100", true));
        MvcResult mvcResult = this.mockMvc.perform(get("/todoList"))
                .andExpect(status().isOk()).andReturn();
        //when
        String contentAsString = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(contentAsString);
        String value = jsonArray.getJSONObject(0).getString("value");
        //then
        Assertions.assertEquals("500",value);

    }

    @Test
    public void should_return_200_when_update() throws Exception {
        //give
        Todo todo = todoListService.addTodo(new Todo("500", true));
        MvcResult mvcResult = this.mockMvc.perform(put("/todoList?key="+ todo.getKey()+"&value=400"))
                .andExpect(status().isOk()).andReturn();
        //when
        int status = mvcResult.getResponse().getStatus();
        //then
        Assertions.assertEquals(200,status);
    }

    @Test
    public void should_return_200_when_delete() throws Exception {
        //give
        Todo todo = todoListService.addTodo(new Todo("500", true));
        MvcResult mvcResult = this.mockMvc.perform(delete("/todoList/"+ todo.getKey()))
                .andExpect(status().isOk()).andReturn();
        //when
        int status = mvcResult.getResponse().getStatus();
        //then
        Assertions.assertEquals(200,status);
    }
}
