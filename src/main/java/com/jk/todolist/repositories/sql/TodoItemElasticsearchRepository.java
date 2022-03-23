package com.jk.todolist.repositories.sql;

import com.jk.todolist.models.TodoItemSql;

import java.util.List;

public interface TodoItemElasticsearchRepository {
    List<TodoItemSql> search(String query);
}
