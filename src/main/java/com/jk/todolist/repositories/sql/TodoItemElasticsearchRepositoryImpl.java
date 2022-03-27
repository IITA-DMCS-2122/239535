package com.jk.todolist.repositories.sql;

import com.jk.todolist.models.sql.TodoItemSql;
import org.hibernate.search.mapper.orm.Search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class TodoItemElasticsearchRepositoryImpl implements TodoItemElasticsearchRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TodoItemSql> search(String query) {
        return Search.session(entityManager)
            .search(TodoItemSql.class)
            .where(f -> f.match()
                .field("title")
                .matching(query)
                .fuzzy())
            .fetchAllHits();
    }
}
