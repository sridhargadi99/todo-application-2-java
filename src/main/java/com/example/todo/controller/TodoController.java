/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;

@RestController
public class TodoController{
    @Autowired
    private TodoH2Service service;

    @GetMapping("/todos")
    public ArrayList<Todo> allTodos(){
        return service.allTodos();
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo){
        return service.addTodo(todo);
    }
    
    @GetMapping("/todos/{id}")
    public Todo getTodo(@PathVariable("id") int id){
        return service.getTodo(id);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") int id, @RequestBody Todo todo){
        return service.upadteTodo(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") int id){
        service.deleteTodo(id);
    }
}
