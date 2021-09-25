package com.example.todo.controllers;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.UserRepository;
import com.example.todo.entities.Task;
import com.example.todo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@RestController
public class UserApi {


    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Autowired
    public UserApi(TaskRepository taskRepository,UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/getTasks")
    public List<Task> getAllTasks(Principal principal){

        Optional<User> user = userRepository.findUserByUsername(principal.getName());

        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return taskRepository.getTasksByUserId(user.get().getId());
    }

    @PostMapping("/saveTask")
    public String saveTask(@RequestBody Task task){
        taskRepository.save(task);
        return "Task Saved!";
    }





}
