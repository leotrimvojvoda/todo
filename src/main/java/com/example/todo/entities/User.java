package com.example.todo.entities;

import com.example.todo.entities.validation.Username;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="users")
@Data
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 3, max=16, message = "Username must be between 3 and 16 characters long")
    @Username
    private String username;
    @Size(min=6, message = "Password must be at least 6 characters long")
    private String password;
    private boolean enabled;

    public User() {
    }

    public User(@Username String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}
