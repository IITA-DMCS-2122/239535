package com.jk.todolist;

import com.jk.todolist.models.TodoItemFromUser;
import com.jk.todolist.models.TodoItemNoSql;
import com.jk.todolist.models.analytics.TodoItemAnalytics;
import com.jk.todolist.models.sql.TodoItemSql;

public class Converter {

    static public TodoItemSql fromUserToSql(TodoItemFromUser itemFromUser){
        return TodoItemSql
            .builder()
            .uuid(itemFromUser.getUuid())
            .title(itemFromUser.getTitle())
            .priority(itemFromUser.getPriority())
            .done(itemFromUser.isDone())
            .build();
    }

    static public TodoItemAnalytics fromSqlToAnalytics(TodoItemSql todoItemSql){
        return TodoItemAnalytics
            .builder()
            .id(todoItemSql.getId())
            .uuid(todoItemSql.getUuid())
            .title(todoItemSql.getTitle())
            .priority(todoItemSql.getPriority())
            .done(todoItemSql.isDone())
            .build();
    }

    static public TodoItemNoSql fromSqlToNoSql(TodoItemSql todoItemSql){
        return TodoItemNoSql
            .builder()
            .id(todoItemSql.getId())
            .uuid(todoItemSql.getUuid())
            .title(todoItemSql.getTitle())
            .priority(todoItemSql.getPriority())
            .done(todoItemSql.isDone())
            .build();
    }
}
