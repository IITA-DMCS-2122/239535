package com.jk.todolist.services;

import com.jk.todolist.Converter;
import com.jk.todolist.models.TodoItemEvent;
import com.jk.todolist.models.sql.TodoItemSql;
import com.jk.todolist.repositories.analytics.TodoItemAnalyticsRepository;
import com.jk.todolist.repositories.sql.TodoItemSqlRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoAuditing
public class MongoListener extends AbstractMongoEventListener<TodoItemEvent> {

    private final TodoItemSqlRepository todoItemSqlRepository;
    private final TodoItemAnalyticsRepository todoItemAnalyticsRepository;

    @Override
    public void onAfterSave(AfterSaveEvent<TodoItemEvent> event) {
        TodoItemSql todoItemSql = Converter.fromUserToSql(event.getSource().getTodoItem());
        todoItemSqlRepository.save(todoItemSql);
        todoItemAnalyticsRepository.save(Converter.fromSqlToAnalytics(todoItemSql));
    }
}
