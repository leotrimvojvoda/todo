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



    @Test
    void contextLoads() {

        User u = new User();
        u.setEnabled(true);
        u.setUsername("leo");
        u.setPassword(passwordEncoder.encode("leo"));


       userRepository.save(u);


        //System.out.println(userRepository.findUserByUsername("user").isPresent());
    }

    @Test
    void existsTest(){
        //System.out.println(userRepository.existsUserByUsername("tom"));

    }

    @Test
    void getIdByUsername(){


        System.out.println("!");

    }

    @Test
    void setUserRepositoryServiceTest(){



        User u = new User("leotrimvojvoda",passwordEncoder.encode("leotrim"),true);


        System.out.println();

       try{
           userRepository.save(u);
       }catch (Exception e){
           e.printStackTrace();
       }


    }

    @Test
    void testPasswordMateches(){
        System.out.println("DO PASSWORDS MATCH ? "+passwordEncoder.matches("leo","$2a$10$qCiSlgin99ydaFQu7LbRqehSp8ucJk3jQORrbgoeyAaKGty4cWFmi"));
    }


}
