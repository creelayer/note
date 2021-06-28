package com.ohmynone.auth.repository;

import com.ohmynone.auth.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUid(UUID uid);
    Optional<Token> findByAccess(String accessToken);
}