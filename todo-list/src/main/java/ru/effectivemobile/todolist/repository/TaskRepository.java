package ru.effectivemobile.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.effectivemobile.todolist.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
