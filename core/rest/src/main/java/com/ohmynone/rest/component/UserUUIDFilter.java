package com.ohmynone.rest.component;

import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.service.IdentityService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class UserUUIDFilter extends OncePerRequestFilter {

    private static final String X_USER = "x-user";

    IdentityService identityService;

    UserUUIDFilter(IdentityService identityService){
        this.identityService = identityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String uuid = "24365930-5197-4d68-a7e0-daa2e9279134";//request.getHeader(X_USER);
        if (uuid == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Identity identity = new Identity(UUID.fromString(uuid));
        UserDetails userDetails = identityService.findOrCreate(identity);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
