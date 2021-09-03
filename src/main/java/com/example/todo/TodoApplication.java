package com.example.todo;

import com.example.todo.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class TodoApplication {


    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);

        //TODO

        /*
        *
        *                               STAGE 1
        * Setup Spring Context
        *
        * Setup H2 Database
        *  Table 1: User(Security), Table 2: Tasks (One Use can have many tasks)
        *
        * Configure Security
        *
        *                               STAGE 2
        *
        * Create the html welcome page
        *  Display header with full name and the logout option
        *
        *  Display the add tasks button
        *
        *  Display the yet to do tasks
        *   Add the Delete Option
        *   Add the Edit Option
        * */


    }

}
