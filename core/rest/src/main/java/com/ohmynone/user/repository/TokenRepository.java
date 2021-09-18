package com.ohmynone.user.repository;

import com.ohmynone.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findOneByAccess(String access);
    Optional<Token> findOneByRefresh(String refresh);
}
