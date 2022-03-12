package com.jk.todolist.services;

import com.jk.todolist.models.TodoItemFromUser;
import com.jk.todolist.models.TodoItemNoSql;
import com.jk.todolist.models.TodoItemSql;
import com.jk.todolist.repositories.TodoItemNoSqlRepository;
import com.jk.todolist.repositories.TodoItemSqlRepository;
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
        var todoItemSql =
                itemSqlRepository.saveAndFlush(
                        TodoItemSql
                                .builder()
                                .uuid(itemFromUser.getUuid())
                                .title(itemFromUser.getTitle())
                                .priority(itemFromUser.getPriority())
                                .done(itemFromUser.isDone())
                                .build());
        itemNoSqlRepository.save(
                TodoItemNoSql
                        .builder()
                        .id(todoItemSql.getId())
                        .uuid(todoItemSql.getUuid())
                        .title(todoItemSql.getTitle())
                        .priority(todoItemSql.getPriority())
                        .done(todoItemSql.isDone())
                        .build());
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

}
