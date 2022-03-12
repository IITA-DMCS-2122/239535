package com.jk.todolist.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemFromUser {

    private String uuid;

    private String title;

    private Integer priority;

    private boolean done;

}
