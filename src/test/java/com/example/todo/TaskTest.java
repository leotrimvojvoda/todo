package com.example.todo;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.UserRepository;
import com.example.todo.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TaskTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void getTask() {

        System.out.println(taskRepository.getTasksByUserId(16).size());

    }

}
