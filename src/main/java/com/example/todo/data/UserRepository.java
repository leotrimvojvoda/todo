package com.example.todo.data;

import com.example.todo.entities.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findUserByUsername(String u);

}
