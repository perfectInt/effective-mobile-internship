package ru.effectivemobile.todolist.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.effectivemobile.todolist.dto.Response;
import ru.effectivemobile.todolist.dto.TaskDto;
import ru.effectivemobile.todolist.entity.Task;
import ru.effectivemobile.todolist.handler.ResourceNotFoundException;
import ru.effectivemobile.todolist.mapper.TaskMapper;
import ru.effectivemobile.todolist.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Cacheable("tasks")
    public Response<List<TaskDto>> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> taskDtos = tasks.stream().map(taskMapper::mapFromEntity).toList();
        return new Response<>(true, taskDtos);
    }

    @Cacheable("tasks")
    public Response<TaskDto> getTaskById(Long id) {
        TaskDto taskDto = taskMapper.mapFromEntity(taskRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
        return new Response<>(true, taskDto);
    }

    @Transactional
    @CachePut(value = "tasks", key = "#taskDto.title")
    public void createTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.mapFromDto(taskDto));
    }

    @Transactional
    @CachePut(value = "tasks", key = "#taskDto.title")
    public void updateTask(TaskDto taskDto) {
        taskRepository.save(taskMapper.mapFromDto(taskDto));
    }

    @Transactional
    @CacheEvict("tasks")
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
