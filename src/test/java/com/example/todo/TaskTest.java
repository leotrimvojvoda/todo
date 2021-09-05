package com.example.todo;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.TaskRepositoryService;
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

    @Autowired
    TaskRepositoryService taskRepositoryService;

    @Test
    void getTask() {

        System.out.println(taskRepository.getTasksByUserId(16).size());

    }

    @Test
    void updateText(){
        try {
            taskRepositoryService.updateTextById(18, "Franch");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
