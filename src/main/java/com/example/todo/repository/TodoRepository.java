// Write your code here
package com.example.todo.repository;

import com.example.todo.model.Todo;
import java.util.*;

public interface TodoRepository{
    ArrayList<Todo> allTodos();
    Todo addTodo(Todo todo);
    Todo getTodo(int id);
    Todo upadteTodo(int id, Todo todo);
    void deleteTodo(int id);

}