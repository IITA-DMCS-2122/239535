package com.jk.todolist.repositories.analytics;

import com.jk.todolist.models.analytics.TodoItemAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TodoItemAnalyticsRepository extends JpaRepository<TodoItemAnalytics, Integer> {
}
