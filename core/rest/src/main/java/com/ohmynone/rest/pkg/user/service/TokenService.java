package com.ohmynone.rest.pkg.user.service;

import com.ohmynone.rest.pkg.user.entity.Token;
import com.ohmynone.rest.pkg.user.entity.User;
import com.ohmynone.rest.pkg.user.repository.TokenRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TokenService {

    protected TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token refresh(String refresh) throws EntityNotFoundException {
        Token oldToken = tokenRepository.findOneByRefresh(refresh)
                .orElseThrow(() -> new EntityNotFoundException("Token not found"));

        if (oldToken.isRefreshExpired()) {
            throw new EntityNotFoundException("Token not found");
        }

        Token token = new Token(oldToken.user);
        tokenRepository.delete(oldToken);
        return tokenRepository.save(token);
    }

    public Token generateByUser(User user) {
        Token token = new Token(user);
        return tokenRepository.save(token);
    }
}
