package com.dyt.responsitory;

import com.dyt.pojo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoListResponsitory  extends JpaRepository<Todo,Integer> {
    @Query(value = "update todo set value = :#{#todo.value} where key = :#{#todo.key}",nativeQuery = true)
    @Modifying
    void updateTodo(@Param("todo") Todo todo);


    Todo findAllByValue(String value);

    void deleteByKey(Integer key);
}

