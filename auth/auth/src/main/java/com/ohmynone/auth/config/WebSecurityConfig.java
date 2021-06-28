package com.ohmynone.auth.config;

import com.ohmynone.auth.component.JsonUsernamePasswordAuthenticationFilter;
import com.ohmynone.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    UserService userService;
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter;

    public WebSecurityConfig(
            UserService userService,
            JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter
    ) {
        this.userService = userService;
        this.jsonUsernamePasswordAuthenticationFilter = jsonUsernamePasswordAuthenticationFilter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/v1/registration", "/v1/auth/refresh").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(
                        jsonUsernamePasswordAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


}
