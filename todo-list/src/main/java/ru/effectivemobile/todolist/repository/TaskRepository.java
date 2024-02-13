package ru.effectivemobile.todolist.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.effectivemobile.todolist.dto.TaskDto;
import ru.effectivemobile.todolist.handler.ResourceNotFoundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final JdbcTemplate jdbc;

    public List<TaskDto> getAll() {
        String query = "SELECT * FROM t_tasks";
        return jdbc.query(query, new BeanPropertyRowMapper<>(TaskDto.class));
    }

    public TaskDto findById(Long id) {
        String query = "SELECT * FROM t_tasks WHERE id = ?";
        return jdbc.query(query, new BeanPropertyRowMapper<>(TaskDto.class), id)
                .stream().findFirst().orElseThrow(ResourceNotFoundException::new);
    }

    public void save(TaskDto task) {
        String query = "INSERT INTO t_tasks (title, description, completed) VALUES (?, ?, ?)";
        jdbc.update(query, task.getTitle(), task.getDescription(), task.getCompleted());
    }

    public void update(TaskDto task) {
        String query = "UPDATE t_tasks SET title = ?, description = ?, completed = ? WHERE id = ?";
        jdbc.update(query, task.getTitle(), task.getDescription(), task.getCompleted(), task.getId());
    }

    public void delete(Long id) {
        String query = "DELETE FROM t_tasks WHERE id = ?";
        jdbc.update(query, id);
    }
}
