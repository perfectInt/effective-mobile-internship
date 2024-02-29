package ru.effectivemobile.todolist.mapper;

import org.springframework.stereotype.Component;
import ru.effectivemobile.todolist.dto.TaskDto;
import ru.effectivemobile.todolist.entity.Task;

@Component
public class TaskMapper {

    public Task mapFromDto(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.getCompleted());
        task.setTitle(taskDto.getTitle());
        return task;
    }

    public TaskDto mapFromEntity(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setCompleted(task.getCompleted());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        return taskDto;
    }
}
