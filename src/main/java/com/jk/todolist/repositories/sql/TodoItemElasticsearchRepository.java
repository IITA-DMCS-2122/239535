package com.jk.todolist.repositories.sql;

import com.jk.todolist.models.sql.TodoItemSql;

import java.util.List;

public interface TodoItemElasticsearchRepository {
    List<TodoItemSql> search(String query);
}
