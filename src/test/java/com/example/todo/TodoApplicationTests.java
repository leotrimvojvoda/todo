package com.example.todo;

import com.example.todo.data.UserRepository;
import com.example.todo.data.UserRepositoryService;
import com.example.todo.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class TodoApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepositoryService userRepositoryService;

    @Test
    void contextLoads() {

        User u = new User();
        u.setEnabled(true);
        u.setUsername("tanya");
        u.setPassword(passwordEncoder.encode("tanya"));


       userRepository.save(u);


        //System.out.println(userRepository.findUserByUsername("user").isPresent());
    }

    @Test
    void existsTest(){
        System.out.println(userRepository.existsUserByUsername("tom"));
    }

    @Test
    void getIdByUsername(){


        System.out.println("!");

    }

    @Test
    void setUserRepositoryServiceTest(){

        System.out.println(">>>>TEST<<<<"+userRepositoryService);

        User u = new User("leotrimvojvoda",passwordEncoder.encode("leotrim"),true);


        System.out.println();

       try{
           userRepository.save(u);
       }catch (Exception e){
           e.printStackTrace();
       }


    }

}
