package com.ohmynone.rest.component;

import com.ohmynone.rest.entity.Identity;
import com.ohmynone.rest.pkg.user.entity.Token;
import com.ohmynone.rest.pkg.user.repository.TokenRepository;
import com.ohmynone.rest.pkg.user.util.TokenUtil;
import com.ohmynone.rest.repository.IdentityRepository;
import com.ohmynone.rest.service.IdentityService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final TokenRepository tokenRepository;
    private final IdentityService identityService;

    public TokenFilter(TokenRepository tokenRepository, IdentityService identityService) {
        this.tokenRepository = tokenRepository;
        this.identityService = identityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String access = request.getHeader(AUTHORIZATION).substring(7);
        Token token = tokenRepository.findOneByAccess(access)
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        if (token.isExpired()) {
            throw new AccessDeniedException("Token expired");
        }

        Identity identity = identityService.findOrCreate(token.user.uuid);

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                identity, null,
                identity == null ? List.of() : identity.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        if (request.getRequestURI().equals("/token")) {
            return true;
        }

        String accessHeader = request.getHeader(AUTHORIZATION);
        if (accessHeader == null || !accessHeader.startsWith("Bearer")) {
            return true;
        }

        return super.shouldNotFilter(request);
    }
}
