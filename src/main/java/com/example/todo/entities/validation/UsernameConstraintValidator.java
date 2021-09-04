package com.example.todo.entities.validation;

import com.example.todo.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameConstraintValidator implements ConstraintValidator<Username, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(">>>>>>>VALIDATING<<<<<<< "+s);
        return !userRepository.existsUserByUsername(s);
    }
}
