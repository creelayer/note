package com.ohmynone.user.service;

import com.ohmynone.user.entity.Token;
import com.ohmynone.user.entity.User;
import com.ohmynone.user.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    protected final TokenRepository tokenRepository;

    public Optional<Token> findByAccess(String access){
        return tokenRepository.findOneByAccess(access);
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
