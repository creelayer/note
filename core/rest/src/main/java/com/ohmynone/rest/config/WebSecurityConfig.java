package com.ohmynone.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmynone.rest.component.TokenAccessFilter;
import com.ohmynone.rest.dto.Response;
import com.ohmynone.rest.pkg.user.component.CustomUserDetails;
import com.ohmynone.rest.pkg.user.entity.Token;
import com.ohmynone.rest.pkg.user.entity.User;
import com.ohmynone.rest.pkg.user.repository.UserRepository;
import com.ohmynone.rest.pkg.user.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserRepository userRepository;

    private final TokenAccessFilter tokenAccessFilter;
    private final ObjectMapper objectMapper;
    private final TokenService tokenService;

    public WebSecurityConfig(TokenService tokenService,
                             UserRepository userRepository,
                             TokenAccessFilter tokenAccessFilter,
                             ObjectMapper objectMapper
    ) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.tokenAccessFilter = tokenAccessFilter;
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder encoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/auth/refresh").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/token")
                .successHandler(this::loginSuccessHandler)
                .failureHandler(this::loginFailureHandler)
                .and()
                .logout().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilterBefore(tokenAccessFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private void loginFailureHandler(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        objectMapper.writeValue(response.getWriter(), new Response<>("Invalid username or password"));
    }

    private void loginSuccessHandler(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        Token token = tokenService.generateByUser((User) authentication.getPrincipal());
        objectMapper.writeValue(response.getWriter(), new Response<>(token));
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetails(userRepository);
    }

}
