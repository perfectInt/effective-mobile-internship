package ru.effectivemobile.todolist.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
}
