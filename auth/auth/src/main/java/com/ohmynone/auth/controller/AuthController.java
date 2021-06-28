package com.ohmynone.auth.controller;

import com.ohmynone.auth.component.UserDetails;
import com.ohmynone.auth.entity.Token;
import com.ohmynone.auth.service.TokenService;
import com.ohmynone.auth.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final TokenService tokenService;
    private JwtUtil jwtUtil;

    public AuthController(TokenService tokenService, JwtUtil jwtUtil) {
        this.tokenService = tokenService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("")
    ResponseEntity<Token> access(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(tokenService.generate(userDetails));
    }

    @PostMapping("/refresh")
    ResponseEntity<Token> refresh(@RequestParam(name = "refresh_token") String refreshToken) throws AccessDeniedException {
        Token token = tokenService.findByUid(jwtUtil.extractId(refreshToken))
                .filter(t -> jwtUtil.validate(t.getRefresh()))
                .orElseThrow(() -> new AccessDeniedException("Invalid refresh token"));

        return ResponseEntity.ok(tokenService.refresh(token));
    }
}
