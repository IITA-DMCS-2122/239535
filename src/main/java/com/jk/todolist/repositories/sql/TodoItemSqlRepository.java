package com.jk.todolist.repositories.sql;

import com.jk.todolist.models.TodoItemSql;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TodoItemSqlRepository extends JpaRepository<TodoItemSql, Integer>, TodoItemElasticsearchRepository {

    void deleteByUuid(String uuid);

}
