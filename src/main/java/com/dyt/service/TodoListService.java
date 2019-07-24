package com.dyt.service;

import com.dyt.pojo.Todo;
import com.dyt.responsitory.TodoListResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TodoListService {

    @Autowired
    private TodoListResponsitory todoListResponsitory;

    public List<Todo> getAllTodoList(){
        return todoListResponsitory.findAll();
    }

    public Todo addTodo(Todo todo){
        return todoListResponsitory.saveAndFlush(todo);
    }

    public void updateTodo(Todo todo){
        todoListResponsitory.updateTodo(todo);
    }

    public void deleteTodoList(Integer key){
        todoListResponsitory.deleteById(key);
    }
}
