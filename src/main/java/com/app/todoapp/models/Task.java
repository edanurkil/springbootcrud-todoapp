package com.app.todoapp.models;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    private Long id;
    private String title;
    private boolean completed;






}