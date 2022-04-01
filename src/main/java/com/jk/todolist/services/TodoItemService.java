package com.jk.todolist.services;

import com.jk.todolist.Converter;
import com.jk.todolist.models.TodoItemFromUser;
import com.jk.todolist.models.TodoItemNoSql;
import com.jk.todolist.models.sql.TodoItemSql;
import com.jk.todolist.repositories.TodoItemNoSqlRepository;
import com.jk.todolist.repositories.analytics.TodoItemAnalyticsRepository;
import com.jk.todolist.repositories.sql.TodoItemSqlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TodoItemService {

    private final TodoItemSqlRepository itemSqlRepository;
    private final TodoItemNoSqlRepository itemNoSqlRepository;
    private final TodoItemAnalyticsRepository itemAnalyticsRepository;

    public TodoItemFromUser getByUuid(String uuid) {
        TodoItemNoSql todoItemNoSql = itemNoSqlRepository.findByUuid(uuid);
        return new TodoItemFromUser(
            todoItemNoSql.getUuid(),
            todoItemNoSql.getTitle(),
            todoItemNoSql.getPriority(),
            todoItemNoSql.isDone());
    }

    public List<TodoItemFromUser> getAll() {
        List<TodoItemNoSql> todoItemsNoSql = itemNoSqlRepository.findAll();
        return todoItemsNoSql
            .stream()
            .map(item -> new TodoItemFromUser(item.getUuid(), item.getTitle(), item.getPriority(), item.isDone()))
            .collect(Collectors.toList());
    }

    public void add(TodoItemFromUser itemFromUser) {
        itemFromUser.setUuid(UUID.randomUUID().toString());
        TodoItemSql todoItemSql = Converter.fromUserToSql(itemFromUser);
        itemSqlRepository.saveAndFlush(todoItemSql);
        itemAnalyticsRepository.save(Converter.fromSqlToAnalytics(todoItemSql));
        itemNoSqlRepository.save(Converter.fromSqlToNoSql(todoItemSql));
    }

    public void deleteByUuid(String uuid) {
        itemSqlRepository.deleteByUuid(uuid);
        itemNoSqlRepository.deleteByUuid(uuid);
    }

    public void update(TodoItemFromUser itemFromUser) {
        TodoItemNoSql itemNoSql = itemNoSqlRepository.findByUuid(itemFromUser.getUuid());
        itemNoSql.setTitle(itemFromUser.getTitle());
        itemNoSql.setPriority(itemFromUser.getPriority());
        itemNoSql.setDone(itemFromUser.isDone());
        itemNoSqlRepository.save(itemNoSql);
        itemSqlRepository.save(
            new TodoItemSql(
                itemNoSql.getId(),
                itemNoSql.getUuid(),
                itemNoSql.getTitle(),
                itemNoSql.getPriority(),
                itemNoSql.isDone()));
    }

    public List<TodoItemFromUser> search(String query) {
        return itemSqlRepository
            .search(query)
            .stream()
            .map(itemSql -> new TodoItemFromUser(itemSql.getUuid(), itemSql.getTitle(), itemSql.getPriority(), itemSql.isDone()))
            .collect(Collectors.toList());
    }

}
