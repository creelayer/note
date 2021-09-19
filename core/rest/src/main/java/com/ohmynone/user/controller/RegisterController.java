package com.ohmynone.user.controller;

import com.ohmynone.user.dto.Response;
import com.ohmynone.user.dto.Register;
import com.ohmynone.user.exception.UserAlreadyExistException;
import com.ohmynone.user.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Register register) {
        try {
            return Response.ok(registrationService.register(register));
        } catch (UserAlreadyExistException e) {
            return Response.error(e.getMessage());
        }
    }

}
