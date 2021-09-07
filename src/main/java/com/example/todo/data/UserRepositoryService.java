package com.example.todo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Deprecated
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserRepositoryService {


    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean existsUserByUsername(String username){
        return userRepository.existsUserByUsername(username);
    }

}
