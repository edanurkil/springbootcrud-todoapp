package com.app.todoapp.controllers;

import com.app.todoapp.dto.TaskDto;
import com.app.todoapp.mapper.TaskMapper;
import com.app.todoapp.models.Category;
import com.app.todoapp.models.Task;
import com.app.todoapp.services.TaskService;
import com.app.todoapp.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final CategoryRepository categoryRepository;

    public TaskController(TaskService taskService, CategoryRepository categoryRepository) {
        this.taskService = taskService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/tasks")
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        System.out.println("Number of Tasks: " + tasks.size());
        tasks.forEach(t -> System.out.println(t.getTitle() + " - Category: " + (t.getCategory() != null ? t.getCategory().getName() : "null")));

        model.addAttribute("tasks", tasks);
        model.addAttribute("taskDTO", new TaskDto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "tasks";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute("taskDTO") @Valid TaskDto taskDTO,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("tasks", taskService.getAllTasks());
            model.addAttribute("categories", categoryRepository.findAll());
            return "tasks";
        }

        Category category = categoryRepository.findByName(taskDTO.getCategoryName())
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(taskDTO.getCategoryName());
                    return categoryRepository.save(newCategory);
                });

        Task task = TaskMapper.toEntity(taskDTO, category);

        taskService.saveTask(task);

        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/tasks";
    }
}
