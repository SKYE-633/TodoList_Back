package com.dyt.controller;

import com.dyt.pojo.Todo;
import com.dyt.service.TodoListService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/todoList")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @GetMapping()
    public ResponseEntity getAllTodoList(){
        List<Todo> todoList = todoListService.getAllTodoList();
        return ResponseEntity.ok().body(todoList);
    }

    @PostMapping()
    public ResponseEntity addTodoList(@RequestBody String str){
        JSONArray array = new JSONArray(str);
        Todo todo = new Todo();
        todo.setValue(array.getJSONObject(0).getString("value"));
        todo.setStatus(array.getJSONObject(0).getBoolean("status"));
        Todo addTodo = todoListService.addTodo(todo);
        return ResponseEntity.ok().body(addTodo);
    }

    @PutMapping(params = {"key","value"})
    public ResponseEntity updateTodoList(@RequestParam("key") int key ,@RequestParam("value") String value){
        Todo todo = new Todo();
        todo.setKey(key);
        todo.setValue(value);
        todoListService.updateTodo(todo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{key}")
    public ResponseEntity deleteTodo(@PathVariable("key") int key){
        todoListService.deleteTodoList(key);
        return ResponseEntity.ok().build();
    }

}
