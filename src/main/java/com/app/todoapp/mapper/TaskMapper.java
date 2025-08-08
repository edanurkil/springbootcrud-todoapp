package com.app.todoapp.mapper;

import com.app.todoapp.dto.TaskDto;
import com.app.todoapp.models.Task;
import com.app.todoapp.models.Category;

public class TaskMapper {
    public static Task toEntity(TaskDto dto, Category category) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setCompleted(false);
        task.setCategory(category);  // Burada category set ediliyor
        return task;
    }
}
