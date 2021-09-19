package com.ohmynone.user.service;

import com.ohmynone.user.dto.Register;
import com.ohmynone.user.entity.User;
import com.ohmynone.user.exception.UserAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    protected final UserService userService;

    public User register(Register register){

        boolean exist = userService.findByUserName(register.username).isPresent();

        if(exist){
            throw new UserAlreadyExistException("User already exist");
        }

        User user = new User();
        user.username = register.username;
        user.password = register.password;

        return userService.save(user);
    }

}
