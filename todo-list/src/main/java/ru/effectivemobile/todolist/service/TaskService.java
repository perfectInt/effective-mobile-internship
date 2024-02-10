package ru.effectivemobile.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.todolist.dto.Response;
import ru.effectivemobile.todolist.dto.TaskDto;
import ru.effectivemobile.todolist.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Cacheable("tasks")
    public Response<List<TaskDto>> getAllTasks() {
        return new Response<>(true, taskRepository.getAll());
    }

    @Cacheable("tasks")
    public Response<TaskDto> getTaskById(Long id) {
        return new Response<>(true, taskRepository.findById(id));
    }

    @Transactional
    @CachePut(value = "tasks", key = "#taskDto.title")
    public void createTask(TaskDto taskDto) {
        taskRepository.save(taskDto);
    }

    @Transactional
    @CachePut(value = "tasks", key = "#taskDto.title")
    public void updateTask(TaskDto taskDto) {
        taskRepository.update(taskDto);
    }

    @Transactional
    @CacheEvict("tasks")
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }
}
