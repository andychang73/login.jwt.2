package com.example.abstractionizer.login.jwt2.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    public static final String secretKey = "fjdiwurjfidjqlicmkoancdhuqicfheoqm";
    public static final String prefix = "Bearer ";
    public static final long validity = 1000 * 60 * 60 * 24;

    public boolean isTokenValid(String token, String username){
        return getClaimFromToken(token, Claims::getSubject).equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return getClaimFromToken(token, Claims::getExpiration).before(new Date());
    }

    public Date getExpiration(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims getAllFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> resolver){
        return resolver.apply(getAllFromToken(token));
    }
}
