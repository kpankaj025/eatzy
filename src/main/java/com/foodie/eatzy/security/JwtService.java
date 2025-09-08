package com.foodie.eatzy.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//to perform operation with jwt
@Service
public class JwtService {

    // in millis for access token
    private static final long EXPIRATION_TIME = 15 * 60 * 1000;
    // for refresh token
    private static final long EXPIRATION_REFRESH_TIME = 24 * 60 * 60 * 1000;
    // secret key
    private static final String SECRET = "dfxserdtfyguhiopklmjnbvcdxfrtfyguhioklmn";

    // generate token

    public String generateToken(String email, boolean isAccessToken) {
        long expTime = isAccessToken ? EXPIRATION_TIME : EXPIRATION_REFRESH_TIME;

        String typeType = isAccessToken ? "Bearer" : "refresh_token";

        String token = Jwts.builder()
                .setSubject(email)
                .claim("typ", typeType)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    // getUsername from token

    public String getUsername(String token) {
        String username = Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getSubject();

        return username;
    }

    // validate token

    public boolean validateToken(String token) {

        if (this.isTokenExpired(token)) {
            return false;
        }

        try {

            Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }

    // check expiration of token

    public boolean isTokenExpired(String token) {

        Date expiration = Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody().getExpiration();

        return expiration.before(new Date());
    }

    public boolean isAccessToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody();

        String type = (String) claims.get("typ");

        return type.equals("Bearer");

    }

    public boolean isRefreshToken(String token) {

        Claims claims = Jwts.parserBuilder().setSigningKey(SECRET.getBytes()).build()
                .parseClaimsJws(token).getBody();

        String type = (String) claims.get("typ");

        return type.equals("refresh_token");

    }

}
