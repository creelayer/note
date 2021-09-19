package com.ohmynone.user.service;

import com.ohmynone.user.entity.User;
import com.ohmynone.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    protected final UserRepository userRepository;

    public Optional<User> findByUserName(String username){
        return userRepository.findOneByUsername(username);
    }

    public User save(User user){
       return userRepository.save(user);
    }
}
