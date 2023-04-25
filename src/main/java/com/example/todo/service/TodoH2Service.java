/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
import com.example.todo.repository.TodoRepository;

@Service
public class TodoH2Service implements TodoRepository{
    @Autowired
    private JdbcTemplate db;

    @Override 
    public ArrayList<Todo> allTodos(){
        List<Todo> todoCollecton = db.query("SELECT * FROM TODOLIST", new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(todoCollecton);
        return todos;
    }

    @Override 
    public Todo addTodo(Todo todo){
        db.update("INSERT INTO TODOLIST(todo, status, priority) VALUES(?,?,?)", todo.getTodo(), todo.getStatus(), todo.getPriority());
        Todo newTodo = db.queryForObject("SELECT * FROM TODOLIST WHERE todo = ? AND status = ? AND priority = ? ", new TodoRowMapper(), todo.getTodo(), todo.getStatus(), todo.getPriority());
        return newTodo;
    }

    @Override
    public Todo getTodo(int id){
        try{
            Todo todoDetails = db.queryForObject("SELECT * FROM TODOLIST WHERE id = ?", new TodoRowMapper(), id);
            return todoDetails;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public Todo upadteTodo(int id, Todo todo){
        if(todo.getTodo() != null){
            db.update("UPDATE TODOLIST SET todo = ? WHERE id = ?", todo.getTodo(),id);
        }
        if(todo.getPriority() != null){
            db.update("UPDATE TODOLIST SET priority = ? WHERE id = ?", todo.getPriority(),id);
        }
        if(todo.getStatus() != null){
            db.update("UPDATE TODOLIST SET status = ? WHERE id = ? ", todo.getStatus(), id);
        }
        return getTodo(id);
    }

    @Override 
    public void deleteTodo(int id){
        db.update("DELETE FROM TODOLIST WHERE id = ?", id);
    }
}

