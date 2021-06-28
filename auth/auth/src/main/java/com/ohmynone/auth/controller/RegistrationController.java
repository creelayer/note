package com.ohmynone.auth.controller;

import com.ohmynone.auth.dto.UserDto;
import com.ohmynone.auth.entity.User;
import com.ohmynone.auth.exception.UserAlreadyExistException;
import com.ohmynone.auth.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    ResponseEntity<User> register(@Valid @RequestBody UserDto dto) throws UserAlreadyExistException {
        User user = registrationService.registerNewUserAccount(dto);
        return ResponseEntity.ok(user);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="There is an account with that email address")
    @ExceptionHandler(UserAlreadyExistException.class)
    public void userAlreadyExistException() {
    }

}
