package ru.effectivemobile.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.effectivemobile.todolist.dto.Response;
import ru.effectivemobile.todolist.dto.TaskDto;
import ru.effectivemobile.todolist.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public Response<List<TaskDto>> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Response<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
    }

    @PutMapping
    public void updateTask(@RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
