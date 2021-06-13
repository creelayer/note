package com.ohmynone.rest.service;

import com.ohmynone.rest.component.CurrentUserDetails;
import com.ohmynone.rest.entity.User;
import com.ohmynone.rest.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService  {

    protected UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(username));

        return new CurrentUserDetails(user);
    }
}
