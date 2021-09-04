package com.example.todo.controllers;

import com.example.todo.data.UserRepository;
import com.example.todo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class HelloController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String hello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        String username = currentPrincipalName.substring(0, 1).toUpperCase() + currentPrincipalName.substring(1);

        model.addAttribute("user",username);

        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    //The BindingResult must be declared right after the Object in this case after the User object

    @PostMapping("/save")
    public String save(@ModelAttribute("user") @Valid User user,
                       BindingResult result, @RequestParam String password2){

        if(result.hasErrors()) return "register"; //  Has no errors so it passes

            userRepository.save(user);
            // Here the error is thrown even though the 'user' has been validated once


        return "login";
    }







}
