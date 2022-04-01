package com.jk.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("todo_items_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemEvent {
    @Id
    private int id;
    private String event;
    private TodoItemFromUser todoItem;
    private String status;
}
