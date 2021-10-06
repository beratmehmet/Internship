package com.obss.onlinemarketplace.annotation;

import com.obss.onlinemarketplace.model.User;
import com.obss.onlinemarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (userRepository!=null){
            User user = userRepository.findByUsername(username);
            if (user!= null){
                return false;
            }

        }
        return true;

    }
}
