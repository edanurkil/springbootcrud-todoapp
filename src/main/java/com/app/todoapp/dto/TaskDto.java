package com.app.todoapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskDto {

    @NotBlank(message = "Başlık boş olamaz")
    @Size(max = 100, message = "Başlık en fazla 100 karakter olabilir")
    private String title;

    private String categoryName;  // <-- Bu satırı ekledik

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
