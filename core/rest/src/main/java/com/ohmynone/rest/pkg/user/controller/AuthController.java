package com.ohmynone.rest.pkg.user.controller;

import com.ohmynone.rest.pkg.user.entity.Token;
import com.ohmynone.rest.pkg.user.service.TokenService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/auth/refresh")
    public Token token(@Valid @RequestBody Token token) {
        try {
            return tokenService.refresh(token.refresh);
        } catch (EntityNotFoundException e) {
            throw new AccessDeniedException("Access denied");
        }
    }
}
