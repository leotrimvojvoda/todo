package com.example.todo.entities;

import lombok.Data;


import javax.persistence.*;

//@Component
@Data
@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String text;

    public Task() {}

    public Task(int id, int userIDForeignKey, String text) {
        this.id = id;
        this.userId = userIDForeignKey;
        this.text = text;
    }
}
