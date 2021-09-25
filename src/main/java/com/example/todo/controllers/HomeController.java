package com.example.todo.controllers;

import com.example.todo.data.TaskRepository;
import com.example.todo.data.TaskRepositoryService;
import com.example.todo.data.UserRepository;
import com.example.todo.entities.Task;
import com.example.todo.entities.User;
import com.example.todo.security.SecurityUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.*;

@Controller
public class HomeController {

    Logger log = LoggerFactory.getLogger(HomeController.class);

     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;
     private final TaskRepository taskRepository;
     private final TaskRepositoryService taskRepositoryService;

     @Autowired
     public HomeController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           TaskRepository taskRepository, TaskRepositoryService taskRepositoryService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.taskRepository = taskRepository;
        this.taskRepositoryService = taskRepositoryService;
     }

    @RequestMapping("/login")
    public String login(Model model){
         model.addAttribute("foundTasks", new ArrayList<Task>());
        return "login";
    }

    @RequestMapping("/")
    public String hello(Model model/*, @RequestParam String search*/){
         log.warn("WARNING");
         log.warn("Do not forget to check if a new username already exists in the database before trying to update it");
         log.info("Double click on one to focus on textArea / edit note");
         log.warn("WARNING");
         //log.info("SEARCH >>> "+search);
        //Get current username and uppercase the first char
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Add username to model
        model.addAttribute("username",username);
        log.info("Logged in as:" +username);
        //Get user id
        Optional<User> u = userRepository.findUserByUsername(username);
        u.orElseThrow(() -> new UsernameNotFoundException("User not found: "+username));

        int id = u.get().getId();
        //Add id to model
        model.addAttribute("userId",id);

        List<Task> tasks = taskRepository.getTasksByUserId(id);

        Collections.reverse(tasks);

        //Add tasks to model
        model.addAttribute("tasks",tasks);

        model.addAttribute("newTask", new Task());

        return "index";
    }

    @RequestMapping("/settings")
    public String settings(@RequestParam String username, Model model){
        model.addAttribute("user", userRepository.findUserByUsername(username.toLowerCase()).get());
        model.addAttribute("username", username);
         return "settings";
    }

    @RequestMapping("/register")
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
            user.setEnabled(true);
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
             task.setText(task.getText().trim());
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

    @Transactional
    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam String id, String userId){

        log.info("Delete task user id > "+userId);

         if(!id.equals("0") || !userId.isBlank()){

             if(!id.equals("0")){
                 taskRepository.deleteById(Integer.parseInt(id));
             }else{
                 taskRepository.deleteAllByUserId(Integer.parseInt(userId));
             }

         }else log.error("Error deleting one or more tasks");
        return "redirect:/";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute("task") Task task){

        log.info(task.toString());

        return "redirect:/";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User u,
                             @RequestParam String newUsername,
                             @RequestParam String currentPassword,
                             @RequestParam String newPassword,
                             Model model){

        if(newUsername.length() >= 3 || (!currentPassword.isBlank() && !newPassword.isBlank())){

             Optional<User> user = userRepository.findUserByUsername(u.getUsername().toLowerCase());
             user.orElseThrow(() -> new UsernameNotFoundException("Username '"+u.getUsername()+"' not found!"));
             User tempUser = user.get();

             if(!userRepository.existsUserByUsername(newUsername)){
                 if( ! newUsername.isBlank()){
                     tempUser.setUsername(newUsername);
                 }else if(!currentPassword.isBlank() && !newPassword.isBlank()){
                     log.info("current and new are not blank");
                     if(passwordEncoder.matches(currentPassword,tempUser.getPassword())) {
                         log.info("current passwords match");
                         tempUser.setPassword(passwordEncoder.encode(newPassword));
                         SecurityUser securityUser = new SecurityUser(tempUser);
                         log.info(securityUser.toString());
                         Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, securityUser.getPassword(), securityUser.getAuthorities());
                         SecurityContextHolder.getContext().setAuthentication(authentication);
                         userRepository.save(tempUser);
                     }
                 }else log.error("/updateUser -> Error changing username or password");
             }log.error("The user \""+newUsername+"\" already exists in the database ");





             model.addAttribute("username", tempUser.getUsername());
             return "/settings";

         }else log.error("Sorry the username or password are long enough");

        model.addAttribute("username", u.getUsername());
         return "/settings";
    }

    @Transactional
    @PostMapping("/deleteUser")
    public String deleteAccount(@RequestParam String id) {

        Optional<User> u = userRepository.findById(Integer.parseInt(id));

        User user = u.orElseThrow(() -> new UsernameNotFoundException("User ["+id+"] not found"));

        taskRepository.deleteAllByUserId(user.getId());
        userRepository.delete(user);

        return "redirect:/logout";
    }

    @PostMapping("/search")
    public String search(){
         log.info(" ±±± SEARCHING ±±±");

         return "redirect:/";
    }
}