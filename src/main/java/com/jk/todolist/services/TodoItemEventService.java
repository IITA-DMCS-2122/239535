package com.jk.todolist.services;

import com.jk.todolist.models.DatabaseSequence;
import com.jk.todolist.models.TodoItemEvent;
import com.jk.todolist.models.TodoItemFromUser;
import com.jk.todolist.repositories.TodoItemEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@AllArgsConstructor
public class TodoItemEventService {

    private final TodoItemEventRepository todoItemEventRepository;
    private final MongoOperations mongoOperations;

    public long countEvents() {
        return todoItemEventRepository.count();
    }

    public void saveEvent(TodoItemFromUser todoItemFromUser) {
        todoItemFromUser.setUuid(java.util.UUID.randomUUID().toString());
        todoItemEventRepository.save(new TodoItemEvent(generateSequence(), "CREATE", todoItemFromUser, "NEW"));
    }

    private int generateSequence() {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is("users_sequence")),
            new Update().inc("seq",1), options().returnNew(true).upsert(true), DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
