package com.example.todo;

import com.example.todo.data.UserRepository;
import com.example.todo.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        u.setUsername("tom");
        u.setPassword(passwordEncoder.encode("tom"));

       //userRepository.save(u);

        System.out.println(userRepository.findUserByUsername("user").isPresent());
    }

}
