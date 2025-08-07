package com.app.todoapp.controllers;

import com.app.todoapp.dto.TaskDto;
import com.app.todoapp.mapper.TaskMapper;
import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskDto", new TaskDto());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute("taskDto") @Valid TaskDto taskDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasks());
            return "tasks";
        }
        Task task = TaskMapper.toEntity(taskDto);
        taskService.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}
