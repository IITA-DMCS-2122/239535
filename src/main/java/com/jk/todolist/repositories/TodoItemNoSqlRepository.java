package com.jk.todolist.repositories;

import com.jk.todolist.models.TodoItemNoSql;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.transaction.Transactional;

@Transactional
public interface TodoItemNoSqlRepository extends MongoRepository<TodoItemNoSql, Integer> {

    TodoItemNoSql findByUuid(String uuid);

    void deleteByUuid(String uuid);

}
