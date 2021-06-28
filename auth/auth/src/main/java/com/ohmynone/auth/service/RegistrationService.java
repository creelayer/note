package com.ohmynone.auth.service;

import com.ohmynone.auth.dto.UserDto;
import com.ohmynone.auth.entity.User;
import com.ohmynone.auth.exception.UserAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    protected UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        return userService.createAccount(userDto);
    }

}
