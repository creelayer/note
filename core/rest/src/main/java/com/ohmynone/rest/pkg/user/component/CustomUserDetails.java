package com.ohmynone.rest.pkg.user.component;

import com.ohmynone.rest.pkg.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetails(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username).orElse(null);
    }
}
