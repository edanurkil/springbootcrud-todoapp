package com.app.todoapp.mapper;

import com.app.todoapp.dto.TaskDto;
import com.app.todoapp.models.Task;

public class TaskMapper {
    public static Task toEntity(TaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setCompleted(false);
        return task;
    }
}