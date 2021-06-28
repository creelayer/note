package com.ohmynone.auth.util;


import com.ohmynone.auth.component.UserDetails;
import com.ohmynone.auth.entity.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@Setter
public class JwtUtil {

    private String secret = "some-special-secret-key";
    private long accessExpirationTime = 1000 * 60 * 5;
    private long refreshExpirationTime = 1000 * 60 * 60 * 24 * 10;

    public String extractId(String token) {
        return extractClaim(token, Claims::getId);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateAccess(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(userDetails.getUid().toString(), claims, new Date(System.currentTimeMillis() + accessExpirationTime));
    }

    public String generateRefresh(Token token) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(token.getUid().toString(), claims, new Date(System.currentTimeMillis() + refreshExpirationTime));
    }

    private String createToken(String id, Map<String, Object> claims, Date expiration) {
        return Jwts.builder().setClaims(claims).setId(id).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean validate(String token) {
        final String id = extractId(token);
        return (id != null && !isTokenExpired(token));
    }
}
