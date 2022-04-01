package com.jk.todolist.repositories;

import com.jk.todolist.models.TodoItemEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemEventRepository extends MongoRepository<TodoItemEvent, Integer> {
}
