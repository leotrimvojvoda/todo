package com.example.todo.controllers;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.TaskRepositoryService;
import com.example.todo.data.UserRepository;
import com.example.todo.entities.Task;
import com.example.todo.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(HelloController.class);

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final TaskRepository taskRepository;
     private final TaskRepositoryService taskRepositoryService;

     @Autowired
     public HelloController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                            TaskRepository taskRepository,TaskRepositoryService taskRepositoryService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
        this.taskRepositoryService = taskRepositoryService;
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
        model.addAttribute("username",username);
        log.info("Logged in as:" +username);
        //Get user id
        Optional<User> u = userRepository.findUserByUsername(currentPrincipalName);
        u.orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));

        int id = u.get().getId();
        //Add id to model
        model.addAttribute("userId",id);

        List<Task> tasks = taskRepository.getTasksByUserId(id);

        //Add tasks to model
        model.addAttribute("tasks",tasks);

        model.addAttribute("newTask", new Task());

        return "index";
    }

    @GetMapping("/settings")
    public String settings(@RequestParam String username, Model model){
        log.error(username);
        model.addAttribute("user", userRepository.findUserByUsername(username.toLowerCase()).get());
        model.addAttribute("username", username);
         return "settings";
    }

    /*
    *  @GetMapping("/settings")
    public String settings(@RequestParam String user, Model model){
        log.info(user);
        model.addAttribute("user", user);
         return "settings";
    }
    * */

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    //The BindingResult must be declared right after the Object in this case after the User object
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("user") User user,BindingResult result, @RequestParam String password2){
         log.info("Trying to save user: "+user.toString());
        if(result.hasErrors()) return "register";

        //Check passwords match
        if(user.getPassword().equals(password2)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else return "register";

        //return to landing page
        return "login";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){
         log.info("Task saved or updated: "+task.toString());
         //If id = 0 than it is a new task, and if id is something else than it is an existing task that will be updated
         if(task.getId() == 0){
             taskRepository.save(task);
         }else {
             try {
                 taskRepositoryService.updateTextById(task.getId(), task.getText());
             }catch (Exception e){
                 e.printStackTrace();
             }
         }

        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam String id){
         taskRepository.deleteById(Integer.parseInt(id));
        return "redirect:/";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task){

        log.info(task.toString());

        return "redirect:/";
    }

}
