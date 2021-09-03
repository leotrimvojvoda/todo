package com.example.todo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserApi {



    @GetMapping("/getCurrentUser")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

}
