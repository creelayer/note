package com.ohmynone.auth.service;

import com.ohmynone.auth.component.UserDetails;
import com.ohmynone.auth.dto.UserDto;
import com.ohmynone.auth.entity.User;
import com.ohmynone.auth.exception.UserAlreadyExistException;
import com.ohmynone.auth.mapper.UserMapper;
import com.ohmynone.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    protected UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<User> findByUserName(String username) {
        return userRepository
                .findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new UserDetails(user);
    }

    public User createAccount(UserDto userDto) throws UserAlreadyExistException {

        if (findByUserName(userDto.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = userMapper.map(userDto);
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        return userRepository.save(user);
    }

}
