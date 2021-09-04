package com.example.todo.controllers;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.UserRepository;
import com.example.todo.entities.Task;
import com.example.todo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
public class HelloController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TaskRepository taskRepository;

    @Autowired
    public HelloController(UserRepository userRepository, PasswordEncoder passwordEncoder, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String hello(Model model){

        //Get current username and uppercase the first char
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        String username = currentPrincipalName.substring(0, 1).toUpperCase() + currentPrincipalName.substring(1);
        //Add username to model
        model.addAttribute("user",username);

        //Get user id
        Optional<User> u = userRepository.findUserByUsername(currentPrincipalName);
        u.orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));

        int id = u.get().getId();
        //Add id to model
        model.addAttribute("id",id);

        List<Task> tasks = taskRepository.getTasksByUserId(id);

        System.out.println(tasks.size());

        //Add tasks to model
        model.addAttribute("tasks",tasks);

        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    //The BindingResult must be declared right after the Object in this case after the User object
    @PostMapping("/save")
    public String save(@ModelAttribute("user") @Valid User user,BindingResult result, @RequestParam String password2){

        if(result.hasErrors()) return "register";

        System.out.println(user.toString());

       /* //Check passwords match
        if(user.getPassword().equals(password2))
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);*/


        //return to landing page
        return "login";
    }

}
