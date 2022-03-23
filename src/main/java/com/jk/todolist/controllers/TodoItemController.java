package com.jk.todolist.controllers;

import com.jk.todolist.models.TodoItemFromUser;
import com.jk.todolist.services.TodoItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping("/get/{uuid}")
    public TodoItemFromUser getItem(@PathVariable String uuid) {
        return todoItemService.getByUuid(uuid);
    }

    @GetMapping("/get/all")
    public List<TodoItemFromUser> getAllItems() {
        return todoItemService.getAll();
    }

    @PostMapping("/add")
    public void addItem(@RequestBody TodoItemFromUser todoItem) {
        todoItemService.add(todoItem);
    }

    @DeleteMapping("/delete/{uuid}")
    public void deleteItem(@PathVariable String uuid) {
        todoItemService.deleteByUuid(uuid);
    }

    @PutMapping("/update")
    public void updateItem(@RequestBody TodoItemFromUser todoItem) {
        todoItemService.update(todoItem);
    }

    @GetMapping("/search/{title}")
    public List<TodoItemFromUser> searchItemsElastic(@PathVariable String title) {
        return todoItemService.search(title);
    }

}
