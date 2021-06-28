package com.ohmynone.auth.service;

import com.ohmynone.auth.component.UserDetails;
import com.ohmynone.auth.entity.Token;
import com.ohmynone.auth.repository.TokenRepository;
import com.ohmynone.auth.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    protected TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    public TokenService(TokenRepository tokenRepository, JwtUtil jwtUtil) {
        this.tokenRepository = tokenRepository;
        this.jwtUtil = jwtUtil;
    }

    public Optional<Token> findByUid(String uid) {
        return tokenRepository.findByUid(UUID.fromString(uid));
    }

    public Token generate(UserDetails userDetails) {
        Token token = new Token();
        token.setUid(UUID.randomUUID());
        token.setUser(userDetails.getUser());
        token.setAccess(jwtUtil.generateAccess(userDetails));
        token.setRefresh(jwtUtil.generateRefresh(token));
        return tokenRepository.save(token);
    }

    public Token refresh(Token oldToken) {
        UserDetails userDetails = new UserDetails(oldToken.getUser());
        Token token = generate(userDetails);
        tokenRepository.delete(oldToken);
        return tokenRepository.save(token);
    }

    public boolean isValid(String token) {
        return jwtUtil.validate(token);
    }
}
